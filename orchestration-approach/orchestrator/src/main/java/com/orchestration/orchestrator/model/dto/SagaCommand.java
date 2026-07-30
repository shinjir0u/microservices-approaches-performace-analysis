package com.orchestration.orchestrator.model.dto;

import java.util.UUID;

public record SagaCommand(
        UUID sagaId,
        UUID orderId,
        String command
) {
}
