package com.choreography.inventory.usecase;

import com.choreography.inventory.event.payment.PaymentFailedEvent;
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
public class RevertInventoryTransactionUseCase {

    private final TransactionService transactionService;

    private final ProcessedEventService processedEventService;

    @Transactional
    public void execute(PaymentFailedEvent paymentFailedEvent) {
        boolean eventProcessed = processedEventService.existsByEventId(UUID.fromString(paymentFailedEvent.eventId()));
        if (eventProcessed) {
            log.info("{} with id: {} is already processed.", paymentFailedEvent.getClass().getSimpleName(), paymentFailedEvent.eventId());
            return;
        }

        transactionService.revertTransactions(paymentFailedEvent);
        processedEventService.saveProcessedEvent(paymentFailedEvent, EventStatus.SUCCESS);
    }

}
