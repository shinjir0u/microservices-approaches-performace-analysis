package com.orchestration.order.listener;

import com.orchestration.order.model.dto.saga.FailCommand;
import com.orchestration.order.model.dto.saga.OrderCommand;
import com.orchestration.order.model.dto.saga.SagaReply;
import com.orchestration.order.model.order.type.Status;
import com.orchestration.order.service.rabbit.RabbitService;
import com.orchestration.order.usecase.UpdateOrderStatusUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    private final RabbitService rabbitService;

    @RabbitListener(queues = "${spring.rabbitmq.order.command.queue}")
    public void handleOrderCommand(OrderCommand orderCommand) {
        log.info("Received orderCommand with orderId: {}", orderCommand.orderId());

        updateOrderStatusUseCase.execute(orderCommand.orderId(), Status.SUCCESS);
        rabbitService.sendOrderSagaReply(SagaReply.from(orderCommand, true));
    }

    @RabbitListener(queues = "${spring.rabbitmq.order.fail.command.queue}")
    public void handleOrderFailCommand(FailCommand orderFailCommand) {
        log.info("Received orderFailCommand with orderId: {}", orderFailCommand.orderId());

        updateOrderStatusUseCase.execute(orderFailCommand.orderId(), Status.FAIL);
        rabbitService.sendOrderSagaReply(SagaReply.from(orderFailCommand, false));
    }

}
