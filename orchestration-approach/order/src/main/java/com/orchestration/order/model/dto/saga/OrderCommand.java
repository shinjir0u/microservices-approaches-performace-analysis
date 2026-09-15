package com.orchestration.order.model.dto.saga;

import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record OrderCommand(
        UUID sagaId,
        UUID orderId
) {
}
