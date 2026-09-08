package com.orchestration.orchestrator.model.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder(toBuilder = true)
public record PaymentCommand(
        UUID sagaId,
        UUID orderId,
        BigDecimal totalAmount
) {
    public static PaymentCommand from(UUID sagaId, SagaStartCommand sagaStartCommand) {
        return PaymentCommand.builder()
                .sagaId(sagaId)
                .orderId(sagaStartCommand.orderId())
                .totalAmount(sagaStartCommand.totalAmount())
                .build();
    }
}
