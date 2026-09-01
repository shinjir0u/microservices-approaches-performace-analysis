package com.orchestration.orchestrator.model.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
public record InventoryCommand(
        UUID sagaId,
        UUID orderId,
        List<Item> items
) {
    public record Item(
            String itemCode,
            Integer quantity
    ) {
    }
}


