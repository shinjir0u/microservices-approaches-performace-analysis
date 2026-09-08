package com.orchestration.orchestrator.service.orchestrator;

import com.orchestration.orchestrator.model.SagaInstance;

public interface OrchestratorService {

    SagaInstance save(SagaInstance sagaInstance);

}
