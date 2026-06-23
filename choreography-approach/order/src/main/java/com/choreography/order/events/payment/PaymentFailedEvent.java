package com.choreography.order.events.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PaymentFailedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId,
        String reason
) {
}
