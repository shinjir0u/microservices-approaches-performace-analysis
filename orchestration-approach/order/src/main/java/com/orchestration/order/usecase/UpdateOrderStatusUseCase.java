package com.orchestration.order.usecase;

import com.orchestration.order.model.order.type.Status;
import com.orchestration.order.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateOrderStatusUseCase {

    private final OrderService orderService;

    public void execute(UUID orderId, Status status) {
        orderService.updateOrderStatus(orderId, status);
    }

}
