package com.choreography.payment.listener;

import com.choreography.payment.events.order.OrderCreatedEvent;
import com.choreography.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final PaymentService paymentService;

    @RabbitListener(queues = "${spring.rabbitmq.order.created.payment.queue}")
    public void receiveOrder(OrderCreatedEvent orderCreatedEvent) {
        log.info("Received orderCreatedEvent with id: {}", orderCreatedEvent.eventId());
        paymentService.chargePayment(orderCreatedEvent.orderId(), orderCreatedEvent.totalAmount());
    }

}
