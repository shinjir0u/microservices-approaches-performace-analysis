package com.orchestration.orchestrator.usecase;

import com.orchestration.orchestrator.model.SagaInstance;
import com.orchestration.orchestrator.model.dto.SagaReply;
import com.orchestration.orchestrator.service.orchestrator.OrchestratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HandleSagaReplyUseCase {

    private final OrchestratorService orchestratorService;

    public SagaInstance execute(SagaReply sagaReply) {
        SagaInstance sagaInstance = orchestratorService.get(sagaReply.sagaId());
        return orchestratorService.save(sagaInstance);
    }
}
