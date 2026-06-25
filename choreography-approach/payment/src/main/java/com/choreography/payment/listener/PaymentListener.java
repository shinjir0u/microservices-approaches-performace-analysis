package com.choreography.payment.listener;

import com.choreography.payment.events.order.OrderCreatedEvent;
import com.choreography.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentListener {

    @Autowired
    private PaymentService paymentService;

    @RabbitListener(queues = "${spring.rabbitmq.order.created.queue}")
    public void receiveOrder(OrderCreatedEvent orderCreatedEvent) {
        log.info("Received orderCreatedEvent with id: {}", orderCreatedEvent.eventId());
        paymentService.chargePayment(orderCreatedEvent.orderId(), orderCreatedEvent.totalAmount());
    }

}
