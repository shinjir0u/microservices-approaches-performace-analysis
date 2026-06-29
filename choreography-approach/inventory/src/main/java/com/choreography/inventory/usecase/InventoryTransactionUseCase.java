package com.choreography.inventory.usecase;

import com.choreography.inventory.events.order.OrderCreatedEvent;
import com.choreography.inventory.model.processedEvent.type.EventStatus;
import com.choreography.inventory.service.processedEvent.ProcessedEventService;
import com.choreography.inventory.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryTransactionUseCase {

    private final TransactionService transactionService;

    private final ProcessedEventService processedEventService;

    @Transactional
    public void execute(OrderCreatedEvent orderCreatedEvent) {
        transactionService.addTransactions(orderCreatedEvent);
        processedEventService.saveProcessedEvent(orderCreatedEvent, EventStatus.SUCCESS);
    }
}
