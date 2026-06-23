package com.choreography.order.service;

import com.choreography.order.model.Order;
import com.choreography.order.model.dto.OrderRequest;
import com.choreography.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(OrderRequest orderRequest) {
        Order order = Order.from(orderRequest);
        return orderRepository.save(order);
    }

}
