package com.orchestration.orchestrator.service.rabbit;

import com.orchestration.orchestrator.config.RabbitMQSetting;
import com.orchestration.orchestrator.model.dto.SagaCommand;
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
    public void sendOrderCommand(SagaCommand sagaCommand) {
        rabbitTemplate.convertAndSend(RabbitMQSetting.ORDER_COMMAND_EXCHANGE, RabbitMQSetting.ORDER_COMMAND_ROUTING_KEY, sagaCommand);
        log.info("Sent order command with sagaId: {}", sagaCommand.sagaId());
    }

    @Override
    public void sendPaymentCommand(SagaCommand sagaCommand) {
        rabbitTemplate.convertAndSend(RabbitMQSetting.PAYMENT_COMMAND_EXCHANGE, RabbitMQSetting.PAYMENT_COMMAND_ROUTING_KEY, sagaCommand);
        log.info("Sent payment command with sagaId: {}", sagaCommand.sagaId());
    }

    @Override
    public void sendInventoryCommand(SagaCommand sagaCommand) {
        rabbitTemplate.convertAndSend(RabbitMQSetting.INVENTORY_COMMAND_EXCHANGE, RabbitMQSetting.INVENTORY_COMMAND_ROUTING_KEY, sagaCommand);
        log.info("Sent inventory command with sagaId: {}", sagaCommand.sagaId());
    }
}
