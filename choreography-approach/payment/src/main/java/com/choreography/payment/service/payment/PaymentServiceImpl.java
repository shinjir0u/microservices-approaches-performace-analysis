package com.choreography.payment.service.payment;

import com.choreography.payment.model.payment.Payment;
import com.choreography.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public Payment getPaymentByOrderId(UUID orderId) {
        return paymentRepository.findByOrderId(orderId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public Payment chargePayment(UUID orderId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Amount must be greater than zero");

        var payment = Payment.builder().orderId(orderId).amount(amount).paidAt(Instant.now()).build();

        Payment savedPayment = paymentRepository.save(payment);
        log.info("Charged payment with id: {}", savedPayment.getId());

        return savedPayment;
    }

    @Override
    @Transactional
    public void revertPayment(UUID orderId) {
        var payment = getPaymentByOrderId(orderId);
        var updatedPayment = payment.toBuilder().amount(null).build();

        Payment savedPayment = paymentRepository.save(updatedPayment);
        log.info("Reverted payment with id: {}", savedPayment.getId());
    }

}
