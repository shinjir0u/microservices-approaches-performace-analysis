package com.orchestration.orchestrator.model.dto;

import com.orchestration.orchestrator.model.SagaInstance;
import lombok.Builder;

import java.util.UUID;

@Builder
public record FailCommand(
        UUID sagaId,
        UUID orderId
) {
    public static FailCommand from(SagaInstance sagaInstance) {
        return FailCommand.builder()
                .sagaId(sagaInstance.getSagaId())
                .orderId(sagaInstance.getOrderId())
                .build();
    }
}
