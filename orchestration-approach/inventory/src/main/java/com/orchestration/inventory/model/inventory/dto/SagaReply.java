package com.orchestration.inventory.model.inventory.dto;

import com.orchestration.inventory.model.inventory.type.ServiceName;
import lombok.Builder;

import java.util.UUID;

@Builder
public record SagaReply(
        UUID sagaId,
        UUID orderId,
        ServiceName serviceName,
        boolean success
) {
}
