package com.orchestration.inventory.model.inventory.dto;

import java.util.UUID;

public record SagaReply(
        UUID sagaId,
        UUID orderId,
        String serviceName,
        boolean success,
        String reason
) {
}
