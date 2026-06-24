package com.choreography.order.events.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PaymentChargedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("payment_id")
        UUID paymentId,
        @JsonProperty("order_id")
        UUID orderId
) {
}
