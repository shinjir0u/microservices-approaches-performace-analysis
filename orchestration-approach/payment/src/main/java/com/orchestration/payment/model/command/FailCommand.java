package com.orchestration.payment.model.command;

import lombok.Builder;

import java.util.UUID;

@Builder
public record FailCommand(
        UUID sagaId,
        UUID orderId
) {
}
