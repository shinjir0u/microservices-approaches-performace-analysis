package com.choreography.payment.usecase;

import com.choreography.payment.event.order.OrderCreatedEvent;
import com.choreography.payment.model.payment.Payment;
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
public class ChargePaymentUseCase {

    private final PaymentService paymentService;

    private final ProcessedEventService processedEventService;

    @Transactional
    public Payment execute(OrderCreatedEvent orderCreatedEvent) {

        boolean eventProcessed = processedEventService.existsByEventId(UUID.fromString(orderCreatedEvent.eventId()));
        if (eventProcessed) {
            log.info("{} with id: {} is already processed.", orderCreatedEvent.getClass().getSimpleName(), orderCreatedEvent.eventId());
            return paymentService.getPaymentByOrderId(orderCreatedEvent.orderId());
        }

        Payment savedPayment = null;
        try {
            savedPayment = paymentService.chargePayment(orderCreatedEvent.orderId(), orderCreatedEvent.totalAmount());
            processedEventService.saveProcessedEvent(orderCreatedEvent, EventStatus.SUCCESS);
        } catch (IllegalArgumentException exception) {
            processedEventService.saveProcessedEvent(orderCreatedEvent, EventStatus.FAIL);
            throw exception;
        }
        return savedPayment;

    }

}
