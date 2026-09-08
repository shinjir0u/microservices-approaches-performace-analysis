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
    @Builder
    public record Item(
            String itemCode,
            Integer quantity
    ) {
    }

    public static InventoryCommand from(UUID sagaId, SagaStartCommand sagaStartCommand) {
        List<Item> items = sagaStartCommand.items().stream().map(
                item -> Item.builder().itemCode(item.itemCode()).quantity(item.quantity()).build()
        ).toList();

        return InventoryCommand.builder()
                .sagaId(sagaId)
                .orderId(sagaStartCommand.orderId())
                .items(items)
                .build();

    }
}


