package com.choreography.order.events.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderCreatedEvent (
        @JsonProperty("event_id")
        String eventId,
        @JsonProperty("order_id")
        UUID orderId,
        BigDecimal totalAmount,
        List<OrderItem> items
    ) {

    public record OrderItem(
            @JsonProperty("item_code")
            String itemCode,
            Integer quantity
    ) {
    }

}
