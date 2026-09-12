package com.orchestration.orchestrator.model.dto;

import java.util.UUID;

public record FailCommand(
        UUID sagaId,
        UUID orderId
) {
}
