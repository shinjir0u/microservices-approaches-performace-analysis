package com.orchestration.inventory.service.rabbit;

import com.orchestration.inventory.model.inventory.dto.SagaReply;
import com.orchestration.inventory.model.inventory.type.ServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitServiceImpl implements RabbitService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.orchestrator.reply.exchange}")
    private String orchestratorInventoryReplyExchange;

    @Value("${spring.rabbitmq.orchestrator.reply.routingKey}")
    private String orchestratorInventoryReplyRoutingKey;

    @Override
    public void sendSagaReplyWithStatus(UUID sagaId, UUID orderId, boolean success) {
        var sagaReply = SagaReply.builder()
                .sagaId(sagaId)
                .orderId(orderId)
                .serviceName(ServiceName.INVENTORY)
                .success(success)
                .build();

        rabbitTemplate.convertAndSend(
                orchestratorInventoryReplyExchange,
                orchestratorInventoryReplyRoutingKey,
                sagaReply
        );
    }

}
