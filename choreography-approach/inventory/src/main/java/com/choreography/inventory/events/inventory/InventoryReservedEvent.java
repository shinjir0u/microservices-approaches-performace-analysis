package com.choreography.inventory.events.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record InventoryReservedEvent(
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId
) {
}
