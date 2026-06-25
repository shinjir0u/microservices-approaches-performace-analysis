package com.choreography.order.model;

import com.choreography.order.model.type.EventStatus;
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

    @Column(name = "order_id")
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "processed_at")
    private Instant processedAt;

}
