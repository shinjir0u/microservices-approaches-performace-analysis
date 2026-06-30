package com.choreography.payment.service.processedEvent;

import com.choreography.payment.event.AppEvent;
import com.choreography.payment.model.processedEvent.type.EventStatus;

public interface ProcessedEventService {

    void saveProcessedEvent(AppEvent event, EventStatus eventStatus);

}
