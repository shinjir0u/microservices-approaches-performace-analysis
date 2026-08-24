package com.choreography.order.service.rabbit;

import com.choreography.order.event.order.OrderCreatedEvent;
import com.choreography.order.event.order.OrderStatusCheckEvent;
import com.choreography.order.model.order.Order;
import com.choreography.order.model.processedEvent.type.EventStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitServiceImpl implements RabbitService {

    @Value("${spring.rabbitmq.order.created.exchange}")
    private String orderCreatedExchange;

    @Value("${spring.rabbitmq.order.created.routingKey}")
    private String orderCreatedRoutingKey;

    @Value("${spring.rabbitmq.order.status.check.exchange}")
    private String orderStatusCheckExchange;

    @Value("${spring.rabbitmq.order.status.check.routingKey}")
    private String orderStatusCheckRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishOrderCreatedEvent(Order order, EventStatus eventStatus) {

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
        rabbitTemplate.convertAndSend(orderCreatedExchange, orderCreatedRoutingKey, orderCreatedEvent);
        log.info("Published orderCreatedEvent with id: {}", orderCreatedEvent.eventId());

    }

    @Override
    public void publishOrderStatusCheckEvent(UUID orderId) {

        var orderStatusCheckEvent = OrderStatusCheckEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .orderId(orderId)
                .build();

        rabbitTemplate.convertAndSend(orderStatusCheckExchange, orderStatusCheckRoutingKey, orderStatusCheckEvent);
        log.info("Published orderStatusCheckEvent with id: {}", orderStatusCheckEvent.eventId());

    }

}
