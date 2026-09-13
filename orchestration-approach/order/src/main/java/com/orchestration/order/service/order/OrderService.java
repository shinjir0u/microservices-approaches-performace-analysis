package com.orchestration.order.service.order;

import com.orchestration.order.model.order.Order;
import com.orchestration.order.model.order.type.Status;

import java.util.UUID;

public interface OrderService {

    Order getOrder(UUID orderId);

    public Order saveOrder(Order order);

    Order updateOrderStatus(UUID orderId, Status status);
}
