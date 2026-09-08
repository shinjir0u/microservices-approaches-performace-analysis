package com.orchestration.order.service.order;

import com.orchestration.order.model.order.Order;
import com.orchestration.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        log.info("Saved order with id: {}", savedOrder.getId());
        return savedOrder;
    }
}

