package com.choreography.inventory.model.processedEvent;

import com.choreography.inventory.model.processedEvent.type.EventStatus;
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
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "processed_at")
    private Instant processedAt;

}
