package com.choreography.payment.repository;

import com.choreography.payment.model.ProcessedEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessedEventRepository extends JpaRepository<ProcessedEvents, UUID> {
}
