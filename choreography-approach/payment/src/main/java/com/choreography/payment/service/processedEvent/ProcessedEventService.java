package com.choreography.payment.service.processedEvent;

import com.choreography.payment.events.order.OrderCreatedEvent;
import com.choreography.payment.model.processedEvent.type.EventStatus;

public interface ProcessedEventService {

    void saveProcessedEvent(OrderCreatedEvent orderCreatedEvent, EventStatus eventStatus);

}
