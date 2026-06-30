package com.choreography.payment.event.inventory;

import com.choreography.payment.event.DomainEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record InventoryReservedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId
) implements DomainEvent {
}
