package com.choreography.payment.service.payment;

import com.choreography.payment.model.payment.Payment;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentService {

    Payment getPaymentByOrderId(UUID orderId);

    Payment chargePayment(UUID orderId, BigDecimal amount);

    @Transactional
    void revertPayment(UUID orderId);
}
