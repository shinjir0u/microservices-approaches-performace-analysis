package com.orchestration.orchestrator.usecase;

import com.orchestration.orchestrator.model.SagaInstance;
import com.orchestration.orchestrator.model.dto.SagaStartCommand;
import com.orchestration.orchestrator.service.orchestrator.OrchestratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartSagaUseCase {

    private final OrchestratorService orchestratorService;

    public SagaInstance execute(SagaStartCommand sagaStartCommand) {
        SagaInstance sagaInstance = SagaInstance.from(sagaStartCommand);
        return orchestratorService.save(sagaInstance);
    }

}
