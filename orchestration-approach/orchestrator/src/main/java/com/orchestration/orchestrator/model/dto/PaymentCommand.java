package com.orchestration.orchestrator.model.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder(toBuilder = true)
public record PaymentCommand(
        UUID sagaId,
        UUID orderId,
        List<InventoryCommand.Item> items
) {
}
