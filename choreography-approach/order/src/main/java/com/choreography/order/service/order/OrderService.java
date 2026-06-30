package com.choreography.order.service.order;

import com.choreography.order.model.dto.OrderRequest;
import com.choreography.order.model.order.Order;

public interface OrderService {

    Order createOrder(OrderRequest orderRequest);

}
