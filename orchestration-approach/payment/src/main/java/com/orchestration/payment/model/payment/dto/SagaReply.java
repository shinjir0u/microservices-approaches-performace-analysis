package com.orchestration.payment.model.payment.dto;

import com.orchestration.payment.model.payment.type.ServiceName;
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
