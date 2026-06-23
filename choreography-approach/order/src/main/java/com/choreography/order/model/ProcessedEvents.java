package com.choreography.order.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "processed_events")
public class ProcessedEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(name = "order_id")
    private UUID orderId;

    private String status;

    @Column(name = "processed_at")
    private Instant processedAt;

}
