package com.choreography.order.event.inventory;

import com.choreography.order.event.DomainEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record InventoryReservedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId
) implements DomainEvent {
}
