package com.orchestration.order.model.dto.saga;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SagaReply(
        UUID sagaId,
        UUID orderId,
        boolean success
) {
    public static SagaReply from(OrderCommand orderCommand, boolean success) {
        return SagaReply.builder()
                .sagaId(orderCommand.sagaId())
                .orderId(orderCommand.orderId())
                .success(success)
                .build();
    }

    public static SagaReply from(FailCommand orderFailCommand, boolean success) {
        return SagaReply.builder()
                .sagaId(orderFailCommand.sagaId())
                .orderId(orderFailCommand.orderId())
                .success(success)
                .build();
    }
}
