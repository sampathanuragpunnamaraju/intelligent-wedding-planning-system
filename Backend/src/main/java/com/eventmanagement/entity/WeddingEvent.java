package com.eventmanagement.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
		name = "wedding_events",
		indexes = {
				@Index(name = "idx_wedding_events__user_id", columnList = "user_id")
		}
)
public class WeddingEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "event_name", nullable = false, length = 150)
	private String eventName;

	@Column(name = "event_date")
	private LocalDate eventDate;

	@Column(name = "location", length = 255)
	private String location;

	@Column(name = "location_decided", nullable = false)
	private Boolean locationDecided;

	@Column(name = "budget", nullable = false, precision = 15, scale = 2)
	private BigDecimal budget;

	@Column(name = "guest_count", nullable = false)
	private Integer guestCount;

	@Column(name = "venue_type", length = 100)
	private String venueType;

	@Column(name = "wedding_scale", length = 100)
	private String weddingScale;

	@Column(name = "wedding_style", length = 100)
	private String weddingStyle;

	@Column(name = "catering_preference", length = 100)
	private String cateringPreference;

	@Column(name = "guests_traveling", nullable = false)
	private Boolean guestsTraveling;

	@Column(name = "parking_importance", length = 100)
	private String parkingImportance;

	@Column(name = "dream_wedding", columnDefinition = "text")
	private String dreamWedding;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 50)
	private WeddingEventStatus status;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@OneToOne(mappedBy = "weddingEvent", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
	private GeneratedPlan generatedPlan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getLocationDecided() {
		return locationDecided;
	}

	public void setLocationDecided(Boolean locationDecided) {
		this.locationDecided = locationDecided;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public Integer getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(Integer guestCount) {
		this.guestCount = guestCount;
	}

	public String getVenueType() {
		return venueType;
	}

	public void setVenueType(String venueType) {
		this.venueType = venueType;
	}

	public String getWeddingScale() {
		return weddingScale;
	}

	public void setWeddingScale(String weddingScale) {
		this.weddingScale = weddingScale;
	}

	public String getWeddingStyle() {
		return weddingStyle;
	}

	public void setWeddingStyle(String weddingStyle) {
		this.weddingStyle = weddingStyle;
	}

	public String getCateringPreference() {
		return cateringPreference;
	}

	public void setCateringPreference(String cateringPreference) {
		this.cateringPreference = cateringPreference;
	}

	public Boolean getGuestsTraveling() {
		return guestsTraveling;
	}

	public void setGuestsTraveling(Boolean guestsTraveling) {
		this.guestsTraveling = guestsTraveling;
	}

	public String getParkingImportance() {
		return parkingImportance;
	}

	public void setParkingImportance(String parkingImportance) {
		this.parkingImportance = parkingImportance;
	}

	public String getDreamWedding() {
		return dreamWedding;
	}

	public void setDreamWedding(String dreamWedding) {
		this.dreamWedding = dreamWedding;
	}

	public WeddingEventStatus getStatus() {
		return status;
	}

	public void setStatus(WeddingEventStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public GeneratedPlan getGeneratedPlan() {
		return generatedPlan;
	}

	public void setGeneratedPlan(GeneratedPlan generatedPlan) {
		this.generatedPlan = generatedPlan;
	}
}
