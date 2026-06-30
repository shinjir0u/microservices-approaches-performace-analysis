package com.choreography.payment.service.payment;

import com.choreography.payment.model.payment.Payment;
import com.choreography.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final RabbitTemplate rabbitTemplate;

    @Override
    @Transactional
    public Payment chargePayment(UUID orderId, BigDecimal amount) {
        var payment = Payment.builder().orderId(orderId).amount(amount).paidAt(Instant.now()).build();

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Charged payment with id: {}", savedPayment.getId());

        return savedPayment;
    }

}
