package com.choreography.order.service.order;

import com.choreography.order.model.order.Order;

import java.util.UUID;

public interface OrderService {

    void processSucceededDomainEvent(UUID orderId);

    Order getOrderById(UUID orderId);

    void saveOrder(Order order);

}
