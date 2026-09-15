package com.orchestration.inventory.model.command;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
public record InventoryCommand(
        UUID sagaId,
        UUID orderId,
        List<Item> items
) {
    @Builder
    public record Item(
            String itemCode,
            Integer quantity
    ) {
    }

}


