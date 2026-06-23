package com.choreography.payment.events.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PaymentChargedEvent(
        @JsonProperty("payment_id")
        UUID paymentId,
        @JsonProperty("order_id")
        UUID orderId
) {
}
