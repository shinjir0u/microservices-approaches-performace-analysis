package com.choreography.order.service.processedEvent;

import com.choreography.order.event.DomainEvent;
import com.choreography.order.model.processedEvent.type.EventStatus;

import java.util.UUID;

public interface ProcessedEventService {

    boolean existsByEventId(UUID eventId);

    void saveDomainEvent(DomainEvent event, EventStatus eventStatus);

}
