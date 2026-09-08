package com.orchestration.order.service.rabbit;

import com.orchestration.order.model.dto.saga.SagaStartCommand;

public interface RabbitService {

    public void sendOrchestratorStartCommand(SagaStartCommand sagaStartCommand);

}
