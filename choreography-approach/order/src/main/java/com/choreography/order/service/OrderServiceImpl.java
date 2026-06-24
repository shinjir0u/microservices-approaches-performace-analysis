package com.choreography.order.service;

import com.choreography.order.events.order.OrderCreatedEvent;
import com.choreography.order.model.Order;
import com.choreography.order.model.dto.OrderRequest;
import com.choreography.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.order.created.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.order.created.routingKey}")
    private String routingKey;

    public Order createOrder(OrderRequest orderRequest) {
        Order order = Order.from(orderRequest);
        Order savedOrder = orderRepository.save(order);

        log.info("Created order with id: {}", savedOrder.getId());

        var orderItems = savedOrder.getOrderItems().stream()
                .map(
                        item ->
                                OrderCreatedEvent.OrderItem.builder()
                                        .itemCode(item.getItemCode()).quantity(item.getQuantity())
                                        .build()
                ).toList();

        var orderCreatedEvent = OrderCreatedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .orderId(savedOrder.getId())
                .totalAmount(savedOrder.getTotalAmount())
                .items(orderItems)
                .build();
        rabbitTemplate.convertAndSend(exchange, routingKey, orderCreatedEvent);

        return savedOrder;
    }

}
