package com.orchestration.orchestrator.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record SagaStartCommand(
        UUID orderId,
        BigDecimal totalAmount,
        List<Item> items
) {
    public record Item(
            String itemCode,
            Integer quantity
    ) {
    }
}
