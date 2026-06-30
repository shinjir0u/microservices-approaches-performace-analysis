package com.choreography.payment.model.processedEvent;

import com.choreography.payment.model.processedEvent.type.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "processed_events")
public class ProcessedEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "event_id")
    private UUID eventId;

    private String name;

    @Column(name = "order_id")
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "processed_at")
    private Instant processedAt;

}
