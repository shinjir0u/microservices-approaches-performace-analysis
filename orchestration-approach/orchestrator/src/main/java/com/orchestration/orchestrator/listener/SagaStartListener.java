package com.orchestration.orchestrator.listener;

import com.orchestration.orchestrator.config.RabbitMQSetting;
import com.orchestration.orchestrator.model.SagaInstance;
import com.orchestration.orchestrator.model.dto.InventoryCommand;
import com.orchestration.orchestrator.model.dto.PaymentCommand;
import com.orchestration.orchestrator.model.dto.SagaStartCommand;
import com.orchestration.orchestrator.service.rabbit.RabbitService;
import com.orchestration.orchestrator.usecase.StartSagaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SagaStartListener {

    private final StartSagaUseCase startSagaUseCase;

    private final RabbitService rabbitService;

    @RabbitListener(queues = RabbitMQSetting.ORCHESTRATOR_START_QUEUE)
    public void receiveSagaStartCommand(SagaStartCommand sagaStartCommand) {
        log.info("Received SagaStartCommand with order id: {}", sagaStartCommand.orderId());
        SagaInstance savedSagaInstance = startSagaUseCase.execute(sagaStartCommand);

        InventoryCommand inventoryCommand = InventoryCommand.from(savedSagaInstance.getSagaId(), sagaStartCommand);
        rabbitService.sendInventoryCommand(inventoryCommand);

        PaymentCommand paymentCommand = PaymentCommand.from(savedSagaInstance.getSagaId(), sagaStartCommand);
        rabbitService.sendPaymentCommand(paymentCommand);
    }

}
