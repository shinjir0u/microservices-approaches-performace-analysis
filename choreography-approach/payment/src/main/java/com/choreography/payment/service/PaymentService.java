package com.choreography.payment.service;

import com.choreography.payment.model.payment.Payment;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentService {

    public Payment chargePayment(UUID orderId, BigDecimal amount);

}
