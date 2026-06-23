package com.choreography.order.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        @JsonProperty("customer_name")
        String customerName,
        @JsonProperty("total_amount")
        BigDecimal totalAmount,
        @JsonProperty("order_items")
        List<OrderItemRequest> orderItemRequests

) {
    public record OrderItemRequest(
            @JsonProperty("item_code")
            String itemCode,
            Integer quantity
    ) {
    }

}
