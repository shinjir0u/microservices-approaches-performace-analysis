package com.choreography.inventory.listener;

import com.choreography.inventory.events.order.OrderCreatedEvent;
import com.choreography.inventory.model.processedEvent.type.EventStatus;
import com.choreography.inventory.service.processedEvent.ProcessedEventService;
import com.choreography.inventory.service.rabbit.RabbitService;
import com.choreography.inventory.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryListener {

    private final TransactionService transactionService;

    private final RabbitService rabbitService;

    private final ProcessedEventService processedEventService;

    @Transactional
    @RabbitListener(queues = "${spring.rabbitmq.order.created.inventory.queue}")
    public void processInventoryTransaction(OrderCreatedEvent orderCreatedEvent) {
        log.info("Received orderCreatedEvent with id: {}", orderCreatedEvent.eventId());
        transactionService.addTransactions(orderCreatedEvent);
        processedEventService.saveProcessedEvent(orderCreatedEvent, EventStatus.SUCCESS);
        rabbitService.publishInventoryReservedEvent(orderCreatedEvent.orderId());
    }

}
