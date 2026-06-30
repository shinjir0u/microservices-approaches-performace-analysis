package com.choreography.inventory.service.processedEvent;

import com.choreography.inventory.event.DomainEvent;
import com.choreography.inventory.model.processedEvent.type.EventStatus;

public interface ProcessedEventService {

    void saveProcessedEvent(DomainEvent event, EventStatus eventStatus);

}
