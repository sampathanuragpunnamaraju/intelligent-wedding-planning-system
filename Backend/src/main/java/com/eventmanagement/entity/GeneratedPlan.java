package com.eventmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

@Entity
@Table(
		name = "generated_plans",
		uniqueConstraints = {
				@UniqueConstraint(name = "uk_generated_plans__wedding_event_id", columnNames = "wedding_event_id")
		}
)
public class GeneratedPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "wedding_event_id", nullable = false, unique = true)
	private WeddingEvent weddingEvent;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "budget_report", nullable = false, columnDefinition = "json")
	private Map<String, Object> budgetReport;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "guest_report", nullable = false, columnDefinition = "json")
	private Map<String, Object> guestReport;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "venue_report", nullable = false, columnDefinition = "json")
	private Map<String, Object> venueReport;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "timeline_report", nullable = false, columnDefinition = "json")
	private Map<String, Object> timelineReport;

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "ai_recommendation_report", nullable = false, columnDefinition = "json")
	private Map<String, Object> aiRecommendationReport;

	@Column(name = "feasibility_score", nullable = false, precision = 6, scale = 2)
	private BigDecimal feasibilityScore;

	@Column(name = "generated_at", nullable = false)
	private LocalDateTime generatedAt;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WeddingEvent getWeddingEvent() {
		return weddingEvent;
	}

	public void setWeddingEvent(WeddingEvent weddingEvent) {
		this.weddingEvent = weddingEvent;
	}

	public Map<String, Object> getBudgetReport() {
		return budgetReport;
	}

	public void setBudgetReport(Map<String, Object> budgetReport) {
		this.budgetReport = budgetReport;
	}

	public Map<String, Object> getGuestReport() {
		return guestReport;
	}

	public void setGuestReport(Map<String, Object> guestReport) {
		this.guestReport = guestReport;
	}

	public Map<String, Object> getVenueReport() {
		return venueReport;
	}

	public void setVenueReport(Map<String, Object> venueReport) {
		this.venueReport = venueReport;
	}

	public Map<String, Object> getTimelineReport() {
		return timelineReport;
	}

	public void setTimelineReport(Map<String, Object> timelineReport) {
		this.timelineReport = timelineReport;
	}

	public Map<String, Object> getAiRecommendationReport() {
		return aiRecommendationReport;
	}

	public void setAiRecommendationReport(Map<String, Object> aiRecommendationReport) {
		this.aiRecommendationReport = aiRecommendationReport;
	}

	public BigDecimal getFeasibilityScore() {
		return feasibilityScore;
	}

	public void setFeasibilityScore(BigDecimal feasibilityScore) {
		this.feasibilityScore = feasibilityScore;
	}

	public LocalDateTime getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(LocalDateTime generatedAt) {
		this.generatedAt = generatedAt;
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
}
