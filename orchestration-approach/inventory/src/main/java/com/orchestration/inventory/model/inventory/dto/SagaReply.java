package com.orchestration.inventory.model.inventory.dto;

import com.orchestration.inventory.model.command.InventoryCommand;
import com.orchestration.inventory.model.inventory.type.ServiceName;
import lombok.Builder;

import java.util.UUID;

@Builder
public record SagaReply(
        UUID sagaId,
        UUID orderId,
        ServiceName serviceName,
        boolean success
) {
    public static SagaReply from(InventoryCommand inventoryCommand, boolean success) {
        return SagaReply.builder()
                .sagaId(inventoryCommand.sagaId())
                .orderId(inventoryCommand.orderId())
                .serviceName(ServiceName.INVENTORY)
                .success(success)
                .build();
    }
}
