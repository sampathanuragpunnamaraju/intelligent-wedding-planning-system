package com.eventmanagement.repository;

import com.eventmanagement.entity.WeddingEvent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeddingEventRepository extends JpaRepository<WeddingEvent, Long> {

	List<WeddingEvent> findByUserId(Long userId);
}
