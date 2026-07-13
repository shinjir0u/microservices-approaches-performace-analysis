package com.choreography.inventory.usecase;

import com.choreography.inventory.event.order.OrderCreatedEvent;
import com.choreography.inventory.model.processedEvent.type.EventStatus;
import com.choreography.inventory.service.processedEvent.ProcessedEventService;
import com.choreography.inventory.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessInventoryTransactionUseCase {

    private final TransactionService transactionService;

    private final ProcessedEventService processedEventService;

    @Transactional
    public void execute(OrderCreatedEvent orderCreatedEvent) {
        boolean eventProcessed = processedEventService.existsByEventId(UUID.fromString(orderCreatedEvent.eventId()));
        if (eventProcessed) {
            log.info("{} with id: {} is already processed.", orderCreatedEvent.getClass().getSimpleName(), orderCreatedEvent.eventId());
            return;
        }

        try {
            transactionService.addTransactions(orderCreatedEvent);
            processedEventService.saveProcessedEvent(orderCreatedEvent, EventStatus.SUCCESS);
        } catch (IllegalArgumentException exception) {
            processedEventService.saveProcessedEvent(orderCreatedEvent, EventStatus.FAIL);
            throw exception;
        }
    }
}
