package com.orchestration.orchestrator.listener;

import com.orchestration.orchestrator.model.SagaInstance;
import com.orchestration.orchestrator.model.dto.FailCommand;
import com.orchestration.orchestrator.model.dto.OrderCommand;
import com.orchestration.orchestrator.model.dto.SagaReply;
import com.orchestration.orchestrator.service.rabbit.RabbitService;
import com.orchestration.orchestrator.usecase.HandleInventorySagaReplyUseCase;
import com.orchestration.orchestrator.usecase.HandleOrderSagaReplyUseCase;
import com.orchestration.orchestrator.usecase.HandlePaymentSagaReplyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SagaReplyListener {

    private final HandleInventorySagaReplyUseCase handleInventorySagaReplyUseCase;

    private final HandlePaymentSagaReplyUseCase handlePaymentSagaReplyUseCase;

    private final HandleOrderSagaReplyUseCase handleOrderSagaReplyUseCase;

    private final RabbitService rabbitService;

    @RabbitListener(queues = "${spring.rabbitmq.orchestrator.inventory.reply.queue}")
    public void handleInventorySagaReply(SagaReply sagaReply) {
        log.info("Received inventorySagaReply with sagaId: {}", sagaReply.sagaId());

        SagaInstance sagaInstance = handleInventorySagaReplyUseCase.execute(sagaReply);
        if (sagaInstance.isInventoryFailed()) {
            FailCommand failCommand = FailCommand.from(sagaInstance);
            rabbitService.sendOrderFailCommand(failCommand);
            rabbitService.sendPaymentFailCommand(failCommand);
            return;
        }

        if (sagaInstance.isAllServicesSucceeded())
            rabbitService.sendOrderCommand(OrderCommand.from(sagaInstance));
    }

    @RabbitListener(queues = "${spring.rabbitmq.orchestrator.payment.reply.queue}")
    public void handlePaymentSagaReply(SagaReply sagaReply) {
        log.info("Received paymentSagaReply with sagaId: {}", sagaReply.sagaId());

        SagaInstance sagaInstance = handlePaymentSagaReplyUseCase.execute(sagaReply);
        if (sagaInstance.isPaymentFailed()) {
            FailCommand failCommand = FailCommand.from(sagaInstance);
            rabbitService.sendOrderFailCommand(failCommand);
            rabbitService.sendInventoryFailCommand(failCommand);
            return;
        }

        if (sagaInstance.isAllServicesSucceeded())
            rabbitService.sendOrderCommand(OrderCommand.from(sagaInstance));
    }

    @RabbitListener(queues = "${spring.rabbitmq.orchestrator.order.reply.queue}")
    public void handleOrderSagaReply(SagaReply sagaReply) {
        log.info("Received orderSagaReply with sagaId: {}", sagaReply.sagaId());

        handleOrderSagaReplyUseCase.execute(sagaReply);
    }

}
