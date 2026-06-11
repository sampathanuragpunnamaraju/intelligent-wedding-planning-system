package com.eventmanagement.repository;

import com.eventmanagement.entity.GeneratedPlan;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneratedPlanRepository extends JpaRepository<GeneratedPlan, Long> {

	Optional<GeneratedPlan> findByWeddingEventId(Long weddingEventId);
}
