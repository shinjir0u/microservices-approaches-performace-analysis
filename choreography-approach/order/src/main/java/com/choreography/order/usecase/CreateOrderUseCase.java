package com.choreography.order.usecase;

import com.choreography.order.model.dto.OrderRequest;
import com.choreography.order.model.order.Order;
import com.choreography.order.model.processedEvent.type.EventStatus;
import com.choreography.order.service.order.OrderService;
import com.choreography.order.service.rabbit.RabbitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderService orderService;

    private final RabbitService rabbitService;

    public void execute(OrderRequest orderRequest) {
        var order = Order.from(orderRequest);
        orderService.saveOrder(order);
        rabbitService.publishOrderCreatedEvent(order, EventStatus.SUCCESS);
    }

}
