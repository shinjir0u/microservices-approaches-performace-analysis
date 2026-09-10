package com.orchestration.inventory.service.rabbit;

import com.orchestration.inventory.config.RabbitMQSetting;
import com.orchestration.inventory.model.inventory.dto.SagaReply;
import com.orchestration.inventory.model.inventory.type.ServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitServiceImpl implements RabbitService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendSagaReplyWithStatus(UUID sagaId, UUID orderId, boolean success) {
        var sagaReply = SagaReply.builder()
                .sagaId(sagaId)
                .orderId(orderId)
                .serviceName(ServiceName.INVENTORY)
                .success(success)
                .build();

        rabbitTemplate.convertAndSend(
                RabbitMQSetting.ORCHESTRATOR_REPLY_EXCHANGE,
                RabbitMQSetting.ORCHESTRATOR_REPLY_ROUTING_KEY,
                sagaReply
        );
    }

}
