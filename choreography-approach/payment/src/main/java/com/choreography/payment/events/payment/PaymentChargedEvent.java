package com.choreography.payment.events.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record PaymentChargedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("payment_id")
        UUID paymentId,
        @JsonProperty("order_id")
        UUID orderId
) {
}
