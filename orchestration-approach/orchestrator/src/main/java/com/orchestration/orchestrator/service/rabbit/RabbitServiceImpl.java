package com.orchestration.orchestrator.service.rabbit;

import com.orchestration.orchestrator.config.RabbitMQSetting;
import com.orchestration.orchestrator.model.dto.FailCommand;
import com.orchestration.orchestrator.model.dto.InventoryCommand;
import com.orchestration.orchestrator.model.dto.OrderCommand;
import com.orchestration.orchestrator.model.dto.PaymentCommand;
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
    public void sendOrderCommand(OrderCommand orderCommand) {
        rabbitTemplate.convertAndSend(RabbitMQSetting.ORDER_COMMAND_EXCHANGE, RabbitMQSetting.ORDER_COMMAND_ROUTING_KEY, orderCommand);
        log.info("Sent order command with sagaId: {}", orderCommand.sagaId());
    }

    @Override
    public void sendFailOrderCommand(FailCommand orderFailCommand) {
        rabbitTemplate.convertAndSend(RabbitMQSetting.ORDER_FAIL_COMMAND_EXCHANGE, RabbitMQSetting.ORDER_FAIL_COMMAND_ROUTING_KEY, orderFailCommand);
        log.info("Sent order fail command with sagaId: {}", orderFailCommand.sagaId());
    }

    @Override
    public void sendPaymentCommand(PaymentCommand paymentCommand) {
        rabbitTemplate.convertAndSend(RabbitMQSetting.PAYMENT_COMMAND_EXCHANGE, RabbitMQSetting.PAYMENT_COMMAND_ROUTING_KEY, paymentCommand);
        log.info("Sent payment command with sagaId: {}", paymentCommand.sagaId());
    }

    @Override
    public void sendPaymentFailCommand(FailCommand paymentFailCommand) {
        rabbitTemplate.convertAndSend(RabbitMQSetting.PAYMENT_FAIL_COMMAND_EXCHANGE, RabbitMQSetting.PAYMENT_FAIL_COMMAND_ROUTING_KEY, paymentFailCommand);
        log.info("Sent payment fail command with sagaId: {}", paymentFailCommand.sagaId());
    }


    @Override
    public void sendInventoryCommand(InventoryCommand inventoryCommand) {
        rabbitTemplate.convertAndSend(RabbitMQSetting.INVENTORY_COMMAND_EXCHANGE, RabbitMQSetting.INVENTORY_COMMAND_ROUTING_KEY, inventoryCommand);
        log.info("Sent inventory command with sagaId: {}", inventoryCommand.sagaId());
    }

    @Override
    public void sendInventoryFailCommand(FailCommand inventoryFailCommand) {
        rabbitTemplate.convertAndSend(RabbitMQSetting.INVENTORY_FAIL_COMMAND_EXCHANGE, RabbitMQSetting.INVENTORY_FAIL_COMMAND_ROUTING_KEY, inventoryFailCommand);
        log.info("Sent inventory fail command with sagaId: {}", inventoryFailCommand.sagaId());
    }

}
