package com.choreography.order.service.rabbit;

import com.choreography.order.model.order.Order;
import com.choreography.order.model.processedEvent.type.EventStatus;

public interface RabbitService {

    void publishOrderCreatedEvent(Order order, EventStatus eventStatus);

}
