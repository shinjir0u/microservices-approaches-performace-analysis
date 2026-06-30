package com.choreography.order.service.order;

import com.choreography.order.model.dto.OrderRequest;
import com.choreography.order.model.order.Order;
import com.choreography.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final RabbitTemplate rabbitTemplate;

    @Override
    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        Order order = Order.from(orderRequest);
        Order savedOrder = orderRepository.save(order);

        log.info("Created order with id: {}", savedOrder.getId());
        return savedOrder;
    }

}
