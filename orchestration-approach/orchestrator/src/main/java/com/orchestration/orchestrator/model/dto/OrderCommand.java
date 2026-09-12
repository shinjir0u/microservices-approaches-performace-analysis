package com.orchestration.orchestrator.model.dto;

import com.orchestration.orchestrator.model.SagaInstance;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record OrderCommand(
        UUID sagaId,
        UUID orderId
) {
    public static OrderCommand from(SagaInstance sagaInstance) {
        return OrderCommand.builder()
                .sagaId(sagaInstance.getSagaId())
                .orderId(sagaInstance.getOrderId())
                .build();
    }
}
