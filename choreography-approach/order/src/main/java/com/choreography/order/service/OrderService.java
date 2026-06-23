package com.choreography.order.service;

import com.choreography.order.model.Order;
import com.choreography.order.model.dto.OrderRequest;

public interface OrderService {

    Order createOrder(OrderRequest orderRequest);

}
