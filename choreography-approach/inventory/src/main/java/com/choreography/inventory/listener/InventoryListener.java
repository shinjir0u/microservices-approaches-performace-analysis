package com.choreography.inventory.listener;

import com.choreography.inventory.event.order.OrderCreatedEvent;
import com.choreography.inventory.service.rabbit.RabbitService;
import com.choreography.inventory.usecase.ProcessInventoryTransactionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryListener {

    private final RabbitService rabbitService;

    private final ProcessInventoryTransactionUseCase inventoryTransactionUseCase;

    @RabbitListener(queues = "${spring.rabbitmq.order.created.inventory.queue}")
    public void processInventoryTransaction(OrderCreatedEvent orderCreatedEvent) {
        log.info("Received orderCreatedEvent with id: {}", orderCreatedEvent.eventId());

        try {
            inventoryTransactionUseCase.execute(orderCreatedEvent);
        } catch (IllegalArgumentException exception) {
            rabbitService.publishInventoryFailedEvent(orderCreatedEvent.orderId(), "Insufficient quantity for order.");
        }
        rabbitService.publishInventoryReservedEvent(orderCreatedEvent.orderId());
    }

}
