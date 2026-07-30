package com.orchestration.orchestrator.model.dto;

import java.util.UUID;

public record SagaReply(
        UUID sagaId,
        UUID orderId,
        String serviceName,
        boolean success,
        String reason
) {
}
