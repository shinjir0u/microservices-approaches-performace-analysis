package com.choreography.payment.usecase;

import com.choreography.payment.events.order.OrderCreatedEvent;
import com.choreography.payment.model.payment.Payment;
import com.choreography.payment.model.processedEvent.type.EventStatus;
import com.choreography.payment.service.payment.PaymentService;
import com.choreography.payment.service.processedEvent.ProcessedEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChargePaymentUseCase {

    private final PaymentService paymentService;

    private final ProcessedEventService processedEventService;

    @Transactional
    public Payment execute(OrderCreatedEvent orderCreatedEvent) {

        Payment savedPayment = paymentService.chargePayment(orderCreatedEvent.orderId(), orderCreatedEvent.totalAmount());
        processedEventService.saveProcessedEvent(orderCreatedEvent, EventStatus.SUCCESS);
        return savedPayment;

    }

}
