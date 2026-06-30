package com.choreography.order.event.payment;

import com.choreography.order.event.AppEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PaymentChargedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("payment_id")
        UUID paymentId,
        @JsonProperty("order_id")
        UUID orderId
) implements AppEvent {
}
