package com.choreography.payment.listener;

import com.choreography.payment.event.order.OrderCreatedEvent;
import com.choreography.payment.model.payment.Payment;
import com.choreography.payment.service.rabbit.RabbitService;
import com.choreography.payment.usecase.ChargePaymentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final ChargePaymentUseCase chargePaymentUseCase;

    private final RabbitService rabbitService;

    @RabbitListener(queues = "${spring.rabbitmq.order.created.payment.queue}")
    public void receiveOrder(OrderCreatedEvent orderCreatedEvent) {
        log.info("Received orderCreatedEvent with id: {}", orderCreatedEvent.eventId());
        Payment payment = chargePaymentUseCase.execute(orderCreatedEvent);
        rabbitService.publishPaymentChargedEvent(payment.getId(), payment.getOrderId());
    }

}
