package com.choreography.inventory.service.processedEvent;

import com.choreography.inventory.event.DomainEvent;
import com.choreography.inventory.model.processedEvent.type.EventStatus;

import java.util.UUID;

public interface ProcessedEventService {

    boolean existsByEventId(UUID eventId);

    void saveProcessedEvent(DomainEvent event, EventStatus eventStatus);

}
