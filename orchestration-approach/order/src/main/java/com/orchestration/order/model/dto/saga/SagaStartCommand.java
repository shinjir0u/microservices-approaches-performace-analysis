package com.orchestration.order.model.dto.saga;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record SagaStartCommand(
        UUID orderId,
        BigDecimal totalAmount,
        List<Item> items
) {
    @Builder
    public record Item(
            String itemCode,
            Integer quantity
    ) {
    }
}
