package com.choreography.order.model.processedEvent;

import com.choreography.order.model.processedEvent.type.EventStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "processed_events")
public class ProcessedEvent {

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
