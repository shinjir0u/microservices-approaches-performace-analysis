package com.orchestration.order.service.rabbit;

import com.orchestration.order.config.RabbitMQSetting;
import com.orchestration.order.model.dto.saga.SagaReply;
import com.orchestration.order.model.dto.saga.SagaStartCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitServiceImpl implements RabbitService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendOrchestratorStartCommand(SagaStartCommand sagaStartCommand) {
        rabbitTemplate.convertAndSend(
                RabbitMQSetting.ORCHESTRATOR_START_EXCHANGE,
                RabbitMQSetting.ORCHESTRATOR_START_ROUTING_KEY,
                sagaStartCommand
        );
    }

    @Override
    public void sendOrderSagaReply(SagaReply sagaReply) {
        rabbitTemplate.convertAndSend(
                RabbitMQSetting.ORCHESTRATOR_ORDER_REPLY_EXCHANGE,
                RabbitMQSetting.ORCHESTRATOR_ORDER_REPLY_ROUTING_KEY,
                sagaReply
        );
    }

}
