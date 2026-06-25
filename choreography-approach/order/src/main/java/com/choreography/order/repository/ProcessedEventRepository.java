package com.choreography.order.repository;

import com.choreography.order.model.ProcessedEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessedEventRepository extends JpaRepository<ProcessedEvents, UUID> {
}
