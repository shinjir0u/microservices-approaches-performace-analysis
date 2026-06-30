package com.choreography.order.event.payment;

import com.choreography.order.event.DomainEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PaymentRefundedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId
) implements DomainEvent {
}
