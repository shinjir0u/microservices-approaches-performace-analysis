package com.choreography.order.controller;

import com.choreography.order.events.order.OrderCreatedEvent;
import com.choreography.order.model.Order;
import com.choreography.order.model.dto.OrderRequest;
import com.choreography.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.order.created.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.order.created.routingKey}")
    private String routingKey;

    @PostMapping("/request")
    public void createOrder(@RequestBody OrderRequest request) {
        Order order = orderService.createOrder(request);
        log.info("Created order with id: {}", order.getId());

        var orderItems = order.getOrderItems().stream()
                .map(
                        item ->
                                OrderCreatedEvent.OrderItem.builder()
                                        .itemCode(item.getItemCode()).quantity(item.getQuantity())
                                        .build()
                ).toList();

        var orderCreatedEvent = OrderCreatedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .orderId(order.getId())
                .totalAmount(order.getTotalAmount())
                .items(orderItems)
                .build();
        rabbitTemplate.convertAndSend(exchange, routingKey, orderCreatedEvent);
    }

}
