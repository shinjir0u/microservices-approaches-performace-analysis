package com.orchestration.orchestrator.listener;

import com.orchestration.orchestrator.config.RabbitMQSetting;
import com.orchestration.orchestrator.model.dto.SagaStartCommand;
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

    @RabbitListener(queues = RabbitMQSetting.ORCHESTRATOR_START_QUEUE)
    public void receiveSagaStartCommand(SagaStartCommand sagaStartCommand) {
        log.info("Received SagaStartCommand with order id: {}", sagaStartCommand.orderId());
        startSagaUseCase.execute(sagaStartCommand);
    }

}
