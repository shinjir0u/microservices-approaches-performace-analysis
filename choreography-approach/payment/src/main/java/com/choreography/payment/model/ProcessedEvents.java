package com.choreography.payment.model;

import com.choreography.payment.model.dto.EventStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "processed_events")
public class ProcessedEvents {

    @Id
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "processed_at")
    private Instant processedAt;

}
