package com.choreography.payment.service.rabbit;

import com.choreography.payment.event.payment.PaymentChargedEvent;
import com.choreography.payment.event.payment.PaymentFailedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitServiceImpl implements RabbitService {

    @Value("${spring.rabbitmq.payment.charged.exchange}")
    private String paymentChargedExchange;

    @Value("${spring.rabbitmq.payment.charged.routingKey}")
    private String paymentChargedRoutingKey;

    @Value("${spring.rabbitmq.payment.failed.exchange}")
    private String paymentFailedExchange;

    @Value("${spring.rabbitmq.payment.failed.routingKey}")
    private String paymentFailedRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishPaymentChargedEvent(UUID paymentId, UUID orderId) {
        var paymentChargedEvent = PaymentChargedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .paymentId(paymentId)
                .orderId(orderId)
                .build();

        rabbitTemplate.convertAndSend(paymentChargedExchange, paymentChargedRoutingKey, paymentChargedEvent);
        log.info("Published paymentChargedEvent with id: {}", paymentChargedEvent.eventId());
    }

    @Override
    public void publishPaymentFailedEvent(UUID orderId, String reason) {
        var paymentFailedEvent = PaymentFailedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .orderId(orderId)
                .reason(reason)
                .build();

        rabbitTemplate.convertAndSend(paymentFailedExchange, paymentFailedRoutingKey, paymentFailedEvent);
        log.info("Published paymentFailedEvent with id: {}", paymentFailedEvent.eventId());
    }

}
