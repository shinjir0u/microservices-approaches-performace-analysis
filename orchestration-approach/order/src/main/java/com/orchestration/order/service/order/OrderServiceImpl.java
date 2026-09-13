package com.orchestration.order.service.order;

import com.orchestration.order.model.order.Order;
import com.orchestration.order.model.order.type.Status;
import com.orchestration.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Order saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        log.info("Saved order with id: {}", savedOrder.getId());
        return savedOrder;
    }

    @Override
    public Order updateOrderStatus(UUID orderId, Status status) {
        Order order = getOrder(orderId);
        order.setStatus(status);

        Order savedOrder = orderRepository.save(order);
        log.info("Updated order with id: {} to status: {}", savedOrder.getId(), savedOrder.getStatus());
        return savedOrder;
    }
}

