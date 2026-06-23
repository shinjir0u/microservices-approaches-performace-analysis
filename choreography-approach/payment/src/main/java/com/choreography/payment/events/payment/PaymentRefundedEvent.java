package com.choreography.payment.events.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PaymentRefundedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId
) {
}
