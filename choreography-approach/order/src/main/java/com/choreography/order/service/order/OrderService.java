package com.choreography.order.service.order;

import com.choreography.order.model.order.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface OrderService {

    void processSucceededDomainEvent(UUID orderId);

    @Transactional
    void processFailedDomainEvent(UUID orderId, String domainEventName);

    Order getOrderById(UUID orderId);

    void saveOrder(Order order);

}
