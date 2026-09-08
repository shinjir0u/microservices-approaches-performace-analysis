package com.orchestration.orchestrator.usecase;

import com.orchestration.orchestrator.model.SagaInstance;
import com.orchestration.orchestrator.model.dto.InventoryCommand;
import com.orchestration.orchestrator.model.dto.PaymentCommand;
import com.orchestration.orchestrator.model.dto.SagaStartCommand;
import com.orchestration.orchestrator.service.orchestrator.OrchestratorService;
import com.orchestration.orchestrator.service.rabbit.RabbitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartSagaUseCase {

    private final OrchestratorService orchestratorService;

    private final RabbitService rabbitService;

    public void execute(SagaStartCommand sagaStartCommand) {
        SagaInstance sagaInstance = SagaInstance.from(sagaStartCommand);
        SagaInstance savedSagaInstance = orchestratorService.save(sagaInstance);

        InventoryCommand inventoryCommand = InventoryCommand.from(savedSagaInstance.getSagaId(), sagaStartCommand);
        rabbitService.sendInventoryCommand(inventoryCommand);

        PaymentCommand paymentCommand = PaymentCommand.from(savedSagaInstance.getSagaId(), sagaStartCommand);
        rabbitService.sendPaymentCommand(paymentCommand);
    }

}
