package com.choreography.order.controller;

import com.choreography.order.model.Order;
import com.choreography.order.model.dto.OrderRequest;
import com.choreography.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    Logger LOG = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.order.created.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.order.created.routingKey}")
    private String routingKey;

    @PostMapping("/request")
    public void createOrder(OrderRequest request) {
        Order order = orderService.createOrder(request);
        LOG.info("Created order with id: " + order.getId());
        rabbitTemplate.convertAndSend(exchange, routingKey, order);
    }

}
