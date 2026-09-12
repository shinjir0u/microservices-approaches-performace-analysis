package com.orchestration.orchestrator.listener;

import com.orchestration.orchestrator.config.RabbitMQSetting;
import com.orchestration.orchestrator.model.SagaInstance;
import com.orchestration.orchestrator.model.dto.FailCommand;
import com.orchestration.orchestrator.model.dto.OrderCommand;
import com.orchestration.orchestrator.model.dto.SagaReply;
import com.orchestration.orchestrator.service.rabbit.RabbitService;
import com.orchestration.orchestrator.usecase.HandleSagaReplyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SagaReplyListener {

    private final HandleSagaReplyUseCase handleSagaReplyUseCase;

    private final RabbitService rabbitService;

    @RabbitListener(queues = RabbitMQSetting.ORCHESTRATOR_INVENTORY_REPLY_QUEUE)
    public void handleInventorySagaReply(SagaReply sagaReply) {
        log.info("Received inventorySagaReply with sagaId: {}", sagaReply.sagaId());

        SagaInstance sagaInstance = handleSagaReplyUseCase.execute(sagaReply);
        if (sagaInstance.isInventoryFailed()) {
            FailCommand failCommand = FailCommand.from(sagaInstance);
            rabbitService.sendOrderFailCommand(failCommand);
            rabbitService.sendPaymentFailCommand(failCommand);
            return;
        }

        if (sagaInstance.isAllServicesSucceeded())
            rabbitService.sendOrderCommand(OrderCommand.from(sagaInstance));
    }

    @RabbitListener(queues = RabbitMQSetting.ORCHESTRATOR_INVENTORY_REPLY_QUEUE)
    public void handlePaymentSagaReply(SagaReply sagaReply) {
        log.info("Received paymentSagaReply with sagaId: {}", sagaReply.sagaId());

        SagaInstance sagaInstance = handleSagaReplyUseCase.execute(sagaReply);
        if (sagaInstance.isPaymentFailed()) {
            FailCommand failCommand = FailCommand.from(sagaInstance);
            rabbitService.sendOrderFailCommand(failCommand);
            rabbitService.sendInventoryFailCommand(failCommand);
            return;
        }

        if (sagaInstance.isAllServicesSucceeded())
            rabbitService.sendOrderCommand(OrderCommand.from(sagaInstance));
    }

}
