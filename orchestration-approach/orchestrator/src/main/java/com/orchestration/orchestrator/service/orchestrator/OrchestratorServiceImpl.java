package com.orchestration.orchestrator.service.orchestrator;

import com.orchestration.orchestrator.model.SagaInstance;
import com.orchestration.orchestrator.repo.SagaInstanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrchestratorServiceImpl implements OrchestratorService {

    private final SagaInstanceRepository sagaInstanceRepository;

    @Override
    public SagaInstance save(SagaInstance sagaInstance) {
        SagaInstance savedSagaInstance = sagaInstanceRepository.save(sagaInstance);
        log.info("Saved SagaInstance with saga id: {}", savedSagaInstance);
        return savedSagaInstance;
    }

}
