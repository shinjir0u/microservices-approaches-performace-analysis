package com.choreography.payment.usecase;

import com.choreography.payment.event.inventory.InventoryFailedEvent;
import com.choreography.payment.model.processedEvent.type.EventStatus;
import com.choreography.payment.service.payment.PaymentService;
import com.choreography.payment.service.processedEvent.ProcessedEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RevertPaymentUseCase {

    private final ProcessedEventService processedEventService;

    private final PaymentService paymentService;

    @Transactional
    public void execute(InventoryFailedEvent inventoryFailedEvent) {
        boolean eventProcessed = processedEventService.existsByEventId(UUID.fromString(inventoryFailedEvent.eventId()));
        if (eventProcessed) {
            log.info("{} with id: {} is already processed.", inventoryFailedEvent.getClass().getSimpleName(), inventoryFailedEvent.eventId());
            return;
        }

        paymentService.revertPayment(inventoryFailedEvent.orderId());
        processedEventService.saveProcessedEvent(inventoryFailedEvent, EventStatus.FAIL);
    }

}
