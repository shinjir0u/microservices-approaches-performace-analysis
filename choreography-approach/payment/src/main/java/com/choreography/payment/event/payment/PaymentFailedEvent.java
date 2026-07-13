package com.choreography.payment.event.payment;

import com.choreography.payment.event.DomainEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record PaymentFailedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId,
        String reason
) implements DomainEvent {
}
