package com.choreography.payment.service.processedEvent;

import com.choreography.payment.event.DomainEvent;
import com.choreography.payment.model.processedEvent.type.EventStatus;

public interface ProcessedEventService {

    void saveProcessedEvent(DomainEvent event, EventStatus eventStatus);

}
