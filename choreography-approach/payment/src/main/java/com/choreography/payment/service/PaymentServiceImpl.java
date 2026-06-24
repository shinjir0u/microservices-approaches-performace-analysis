package com.choreography.payment.service;

import com.choreography.payment.model.Payment;
import com.choreography.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment chargePayment(UUID orderId, BigDecimal amount) {
        var payment = Payment.builder().orderId(orderId).amount(amount).paidAt(Instant.now()).build();
        return paymentRepository.save(payment);
    }

}
