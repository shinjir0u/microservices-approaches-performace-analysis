package com.choreography.inventory.event.payment;

import com.choreography.inventory.event.AppEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PaymentRefundedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId
) implements AppEvent {
}
