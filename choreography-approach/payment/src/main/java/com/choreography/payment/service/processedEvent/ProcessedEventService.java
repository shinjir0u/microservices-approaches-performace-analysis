package com.choreography.payment.service.processedEvent;

import com.choreography.payment.event.DomainEvent;
import com.choreography.payment.model.processedEvent.type.EventStatus;

import java.util.UUID;

public interface ProcessedEventService {

    boolean existsByEventId(UUID eventId);

    void saveProcessedEvent(DomainEvent event, EventStatus eventStatus);

}
