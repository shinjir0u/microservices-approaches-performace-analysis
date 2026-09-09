package com.orchestration.orchestrator.model.dto;

import com.orchestration.orchestrator.model.type.ServiceName;

import java.util.UUID;

public record SagaReply(
        UUID sagaId,
        UUID orderId,
        ServiceName serviceName,
        boolean success
) {
}
