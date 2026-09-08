package com.orchestration.order.model.dto.saga;

import com.orchestration.order.model.order.Order;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record SagaStartCommand(
        UUID orderId,
        BigDecimal totalAmount,
        List<Item> items
) {
    @Builder
    public record Item(
            String itemCode,
            Integer quantity
    ) {
    }

    public static SagaStartCommand from(Order order) {
        List<SagaStartCommand.Item> items = order.getOrderItems().stream().map(
                orderItem -> SagaStartCommand.Item.builder()
                        .itemCode(orderItem.getItemCode())
                        .quantity(orderItem.getQuantity())
                        .build()
        ).toList();

        return SagaStartCommand.builder()
                .orderId(order.getId())
                .totalAmount(order.getTotalAmount())
                .items(items)
                .build();
    }
}
