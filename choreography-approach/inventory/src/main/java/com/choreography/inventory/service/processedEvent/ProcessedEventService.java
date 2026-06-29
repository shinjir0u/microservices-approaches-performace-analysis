package com.choreography.inventory.service.processedEvent;

import com.choreography.inventory.events.order.OrderCreatedEvent;
import com.choreography.inventory.model.processedEvent.type.EventStatus;

public interface ProcessedEventService {

    void saveProcessedEvent(OrderCreatedEvent orderCreatedEvent, EventStatus eventStatus);

}
