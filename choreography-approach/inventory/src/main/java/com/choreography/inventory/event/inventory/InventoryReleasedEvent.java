package com.choreography.inventory.event.inventory;

import com.choreography.inventory.event.AppEvent;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record InventoryReleasedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId
) implements AppEvent {
}
