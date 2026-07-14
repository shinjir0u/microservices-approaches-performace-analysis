package com.choreography.inventory.service.rabbit;

import com.choreography.inventory.event.inventory.InventoryFailedEvent;
import com.choreography.inventory.event.inventory.InventoryReservedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitServiceImpl implements RabbitService {

    @Value("${spring.rabbitmq.inventory.reserved.exchange}")
    private String inventoryReservedExchange;

    @Value("${spring.rabbitmq.inventory.reserved.routingKey}")
    private String inventoryReservedRoutingKey;

    @Value("${spring.rabbitmq.inventory.failed.exchange}")
    private String inventoryFailedExchange;

    @Value("${spring.rabbitmq.inventory.failed.routingKey}")
    private String inventoryFailedRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public void publishInventoryReservedEvent(UUID orderId) {
        InventoryReservedEvent inventoryReservedEvent = InventoryReservedEvent
                .builder()
                .eventId(UUID.randomUUID().toString())
                .orderId(orderId)
                .build();

        rabbitTemplate.convertAndSend(inventoryReservedExchange, inventoryReservedRoutingKey, inventoryReservedEvent);
        log.info("Published inventoryReservedEvent with id: {}", inventoryReservedEvent.eventId());
    }

    @Override
    public void publishInventoryFailedEvent(UUID orderId, String reason) {
        InventoryFailedEvent inventoryFailedEvent = InventoryFailedEvent
                .builder()
                .eventId(UUID.randomUUID().toString())
                .orderId(orderId)
                .reason(reason)
                .build();

        rabbitTemplate.convertAndSend(inventoryFailedExchange, inventoryFailedRoutingKey, inventoryFailedEvent);
        log.info("Published inventoryFailedEvent with id: {}", inventoryFailedEvent.eventId());

    }

}
