package com.orchestration.order.service.rabbit;

import com.orchestration.order.model.dto.saga.SagaReply;
import com.orchestration.order.model.dto.saga.SagaStartCommand;

public interface RabbitService {

    public void sendOrchestratorStartCommand(SagaStartCommand sagaStartCommand);

    void sendOrderSagaReply(SagaReply sagaReply);
}
