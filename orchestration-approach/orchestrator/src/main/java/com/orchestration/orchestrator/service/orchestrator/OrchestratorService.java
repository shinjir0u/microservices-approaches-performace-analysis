package com.orchestration.orchestrator.service.orchestrator;

import com.orchestration.orchestrator.model.SagaInstance;

import java.util.UUID;

public interface OrchestratorService {

    SagaInstance get(UUID sagaId);

    SagaInstance save(SagaInstance sagaInstance);

}
