package com.choreography.inventory.service.processedEvent;

import com.choreography.inventory.event.AppEvent;
import com.choreography.inventory.model.processedEvent.type.EventStatus;

public interface ProcessedEventService {

    void saveProcessedEvent(AppEvent event, EventStatus eventStatus);

}
