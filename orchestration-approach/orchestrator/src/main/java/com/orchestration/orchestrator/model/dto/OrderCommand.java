package com.orchestration.orchestrator.model.dto;

import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record OrderCommand(
        UUID sagaId,
        UUID orderId
) {
}
