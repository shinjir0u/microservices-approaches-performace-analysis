package com.choreography.order.service.processedEvent;

import com.choreography.order.event.DomainEvent;
import com.choreography.order.model.processedEvent.type.EventStatus;

public interface ProcessedEventService {

    void saveDomainEvent(DomainEvent event, EventStatus eventStatus);

}
