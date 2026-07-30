package com.orchestration.orchestrator.service.rabbit;

import com.orchestration.orchestrator.model.dto.SagaCommand;

public interface RabbitService {

    void sendOrderCommand(SagaCommand sagaCommand);

    void sendPaymentCommand(SagaCommand sagaCommand);

    void sendInventoryCommand(SagaCommand sagaCommand);

}
