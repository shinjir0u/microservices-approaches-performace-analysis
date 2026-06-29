package com.choreography.inventory.listener;

import com.choreography.inventory.events.order.OrderCreatedEvent;
import com.choreography.inventory.model.processedEvent.ProcessedEvent;
import com.choreography.inventory.model.processedEvent.type.EventStatus;
import com.choreography.inventory.repository.ProcessedEventRepository;
import com.choreography.inventory.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryListener {

    private final TransactionService transactionService;

    private final ProcessedEventRepository processedEventRepository;

    @RabbitListener(queues = "${spring.rabbitmq.order.created.inventory.queue}")
    public void receiveOrder(OrderCreatedEvent orderCreatedEvent) {
        log.info("Received orderCreatedEvent with id: {}", orderCreatedEvent.eventId());
        transactionService.addTransactions(orderCreatedEvent);

        ProcessedEvent processedEvent = ProcessedEvent
                .builder()
                .id(UUID.fromString(orderCreatedEvent.eventId()))
                .name(orderCreatedEvent.getClass().getName())
                .orderId(orderCreatedEvent.orderId())
                .status(EventStatus.SUCCESS)
                .processedAt(Instant.now())
                .build();
        ProcessedEvent savedProcessEvent = processedEventRepository.save(processedEvent);
        log.info("Processed orderCreatedEvent with order id: {}", savedProcessEvent.getOrderId());
    }

}
