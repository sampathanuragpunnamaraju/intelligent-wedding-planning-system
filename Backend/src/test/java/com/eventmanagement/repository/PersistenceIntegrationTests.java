package com.eventmanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.eventmanagement.entity.GeneratedPlan;
import com.eventmanagement.entity.User;
import com.eventmanagement.entity.WeddingEvent;
import com.eventmanagement.entity.WeddingEventStatus;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest(properties = {
		"spring.datasource.url=${DB_URL}",
		"spring.datasource.username=${DB_USERNAME}",
		"spring.datasource.password=${DB_PASSWORD}",
		"spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
		"spring.flyway.enabled=true",
		"spring.flyway.locations=classpath:db/migration",
		"spring.jpa.hibernate.ddl-auto=validate",
		"spring.sql.init.mode=never"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnabledIfEnvironmentVariable(named = "DB_URL", matches = ".+")
@EnabledIfEnvironmentVariable(named = "DB_USERNAME", matches = ".+")
@EnabledIfEnvironmentVariable(named = "DB_PASSWORD", matches = ".+")
class PersistenceIntegrationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WeddingEventRepository weddingEventRepository;

	@Autowired
	private GeneratedPlanRepository generatedPlanRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void persistsUser() {
		User user = createUser("user-persistence@example.com");

		User savedUser = userRepository.saveAndFlush(user);
		clearPersistenceContext();

		assertThat(userRepository.findById(savedUser.getId()))
				.isPresent()
				.get()
				.satisfies(foundUser -> {
					assertThat(foundUser.getName()).isEqualTo("Test User");
					assertThat(foundUser.getEmail()).isEqualTo("user-persistence@example.com");
					assertThat(foundUser.getPasswordHash()).isEqualTo("hashed-password");
					assertThat(foundUser.getCreatedAt()).isNotNull();
					assertThat(foundUser.getUpdatedAt()).isNotNull();
				});
	}

	@Test
	void persistsWeddingEventOwnershipAndStatus() {
		User user = userRepository.saveAndFlush(createUser("event-owner@example.com"));
		WeddingEvent weddingEvent = createWeddingEvent(user, WeddingEventStatus.QUESTIONNAIRE_COMPLETED);

		WeddingEvent savedEvent = weddingEventRepository.saveAndFlush(weddingEvent);
		clearPersistenceContext();

		List<WeddingEvent> events = weddingEventRepository.findByUserId(user.getId());

		assertThat(events).hasSize(1);
		assertThat(events.getFirst().getId()).isEqualTo(savedEvent.getId());
		assertThat(events.getFirst().getUser().getId()).isEqualTo(user.getId());
		assertThat(events.getFirst().getStatus()).isEqualTo(WeddingEventStatus.QUESTIONNAIRE_COMPLETED);
	}

	@Test
	void persistsGeneratedPlanOneToOneMappingAndJsonFields() {
		User user = userRepository.saveAndFlush(createUser("plan-owner@example.com"));
		WeddingEvent weddingEvent = weddingEventRepository.saveAndFlush(
				createWeddingEvent(user, WeddingEventStatus.PLAN_GENERATED));
		GeneratedPlan generatedPlan = createGeneratedPlan(weddingEvent);

		GeneratedPlan savedPlan = generatedPlanRepository.saveAndFlush(generatedPlan);
		clearPersistenceContext();

		assertThat(generatedPlanRepository.findByWeddingEventId(weddingEvent.getId()))
				.isPresent()
				.get()
				.satisfies(foundPlan -> {
					assertThat(foundPlan.getId()).isEqualTo(savedPlan.getId());
					assertThat(foundPlan.getWeddingEvent().getId()).isEqualTo(weddingEvent.getId());
					assertThat(foundPlan.getFeasibilityScore()).isEqualByComparingTo("87.50");
					assertThat(foundPlan.getBudgetReport()).containsEntry("status", "FEASIBLE");
					assertThat(foundPlan.getGuestReport()).containsEntry("guestCount", 120);
					assertThat(foundPlan.getVenueReport()).containsEntry("venueType", "BANQUET_HALL");
					assertThat(foundPlan.getTimelineReport()).containsEntry("monthsUntilWedding", 9);
					assertThat(foundPlan.getAiRecommendationReport()).containsEntry("summary", "Plan is achievable.");
				});
	}

	private User createUser(String email) {
		User user = new User();
		user.setName("Test User");
		user.setEmail(email);
		user.setPasswordHash("hashed-password");
		return user;
	}

	private WeddingEvent createWeddingEvent(User user, WeddingEventStatus status) {
		WeddingEvent weddingEvent = new WeddingEvent();
		weddingEvent.setUser(user);
		weddingEvent.setEventName("Test Wedding");
		weddingEvent.setEventDate(LocalDate.of(2027, 2, 14));
		weddingEvent.setLocation("Bengaluru");
		weddingEvent.setLocationDecided(true);
		weddingEvent.setBudget(new BigDecimal("1500000.00"));
		weddingEvent.setGuestCount(120);
		weddingEvent.setVenueType("BANQUET_HALL");
		weddingEvent.setWeddingScale("MEDIUM");
		weddingEvent.setWeddingStyle("TRADITIONAL");
		weddingEvent.setCateringPreference("VEGETARIAN");
		weddingEvent.setGuestsTraveling(true);
		weddingEvent.setParkingImportance("HIGH");
		weddingEvent.setDreamWedding("A warm family wedding with elegant decor.");
		weddingEvent.setStatus(status);
		return weddingEvent;
	}

	private GeneratedPlan createGeneratedPlan(WeddingEvent weddingEvent) {
		GeneratedPlan generatedPlan = new GeneratedPlan();
		generatedPlan.setWeddingEvent(weddingEvent);
		generatedPlan.setBudgetReport(Map.of("status", "FEASIBLE", "estimatedCost", 1400000));
		generatedPlan.setGuestReport(Map.of("guestCount", 120, "staffRequired", 12));
		generatedPlan.setVenueReport(Map.of("venueType", "BANQUET_HALL", "capacityOk", true));
		generatedPlan.setTimelineReport(Map.of("monthsUntilWedding", 9, "risk", "LOW"));
		generatedPlan.setAiRecommendationReport(Map.of("summary", "Plan is achievable.", "alternative", "Keep guest list stable."));
		generatedPlan.setFeasibilityScore(new BigDecimal("87.50"));
		generatedPlan.setGeneratedAt(LocalDateTime.now());
		return generatedPlan;
	}

	private void clearPersistenceContext() {
		entityManager.clear();
	}
}
