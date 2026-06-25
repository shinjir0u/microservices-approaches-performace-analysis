package com.choreography.payment.service;

import com.choreography.payment.events.payment.PaymentChargedEvent;
import com.choreography.payment.model.Payment;
import com.choreography.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.payment.charged.exchange}")
    private String paymentChargedExchange;

    @Value("${spring.rabbitmq.payment.charged.routingKey}")
    private String paymentChargedRoutingKey;

    @Override
    public Payment chargePayment(UUID orderId, BigDecimal amount) {
        var payment = Payment.builder().orderId(orderId).amount(amount).paidAt(Instant.now()).build();

        var paymentChargedEvent = PaymentChargedEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .paymentId(payment.getId())
                .orderId(payment.getOrderId())
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Charged payment with id: {}", savedPayment.getId());

        rabbitTemplate.convertAndSend(paymentChargedExchange, paymentChargedRoutingKey, paymentChargedEvent);
        log.info("Published paymentChargedEvent with id: {}", paymentChargedEvent.eventId());

        return savedPayment;
    }

}
