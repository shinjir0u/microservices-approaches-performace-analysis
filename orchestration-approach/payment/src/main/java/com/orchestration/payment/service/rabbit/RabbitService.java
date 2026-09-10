package com.orchestration.payment.service.rabbit;

import java.util.UUID;

public interface RabbitService {

    void sendSagaReplyWithStatus(UUID sagaId, UUID orderId, boolean success);

}
