package com.orchestration.order.model.dto.saga;

import java.util.UUID;

public record SagaReply(
        UUID sagaId,
        UUID orderId,
        String serviceName,
        boolean success,
        String reason
) {
}
