package com.choreography.order.service.processedEvent;

import com.choreography.order.event.AppEvent;
import com.choreography.order.model.processedEvent.type.EventStatus;

public interface ProcessedEventService {

    void saveAppEvent(AppEvent event, EventStatus eventStatus);

}
