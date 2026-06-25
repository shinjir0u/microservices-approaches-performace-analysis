package com.choreography.order.listener;

import com.choreography.order.events.payment.PaymentChargedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderListener {

    @RabbitListener(queues = "${spring.rabbitmq.payment.charged.queue}")
    public void receivePayment(PaymentChargedEvent paymentChargedEvent) {
        log.info("Received paymentChargedEvent with id: {}", paymentChargedEvent.eventId());
    }

}
