package com.choreography.payment.events.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record InventoryFailedEvent (
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId,
        String reason
) {
}
