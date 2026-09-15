package com.orchestration.order.service.rabbit;

import com.orchestration.order.model.dto.saga.SagaReply;
import com.orchestration.order.model.dto.saga.SagaStartCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitServiceImpl implements RabbitService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.orchestrator.start.exchange}")
    private String orchestratorStartExchange;

    @Value("${spring.rabbitmq.orchestrator.start.routingKey}")
    private String orchestratorStartRoutingKey;

    @Value("${spring.rabbitmq.orchestrator.reply.exchange}")
    private String orchestratorOrderReplyExchange;

    @Value("${spring.rabbitmq.orchestrator.reply.routingKey}")
    private String orchestratorOrderReplyRoutingKey;

    @Override
    public void sendOrchestratorStartCommand(SagaStartCommand sagaStartCommand) {
        rabbitTemplate.convertAndSend(
                orchestratorStartExchange,
                orchestratorStartRoutingKey,
                sagaStartCommand
        );
    }

    @Override
    public void sendOrderSagaReply(SagaReply sagaReply) {
        rabbitTemplate.convertAndSend(
                orchestratorOrderReplyExchange,
                orchestratorOrderReplyRoutingKey,
                sagaReply
        );
    }

}
