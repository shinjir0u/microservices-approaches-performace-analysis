package com.orchestration.order.usecase;

import com.orchestration.order.model.dto.order.OrderRequest;
import com.orchestration.order.model.order.Order;
import com.orchestration.order.service.order.OrderService;
import com.orchestration.order.service.rabbit.RabbitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateOrderUsecase {

    private final OrderService orderService;

    private final RabbitService rabbitService;

    public void execute(OrderRequest orderRequest) {
        Order order = Order.from(orderRequest);
        Order savedOrder = orderService.saveOrder(order);
        rabbitService.sendOrchestratorStartCommand(Order.toSagaStartCommand(savedOrder));
    }

}
