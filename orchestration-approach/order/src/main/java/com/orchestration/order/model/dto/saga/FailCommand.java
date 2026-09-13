package com.orchestration.order.model.dto.saga;

import lombok.Builder;

import java.util.UUID;

@Builder
public record FailCommand(
        UUID sagaId,
        UUID orderId
) {
}
