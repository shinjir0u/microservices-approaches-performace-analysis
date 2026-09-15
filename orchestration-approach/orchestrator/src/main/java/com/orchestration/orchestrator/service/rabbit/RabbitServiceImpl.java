package com.orchestration.orchestrator.service.rabbit;

import com.orchestration.orchestrator.model.dto.FailCommand;
import com.orchestration.orchestrator.model.dto.InventoryCommand;
import com.orchestration.orchestrator.model.dto.OrderCommand;
import com.orchestration.orchestrator.model.dto.PaymentCommand;
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

    @Value("${spring.rabbitmq.order.command.exchange}")
    private String orderCommandExchange;

    @Value("${spring.rabbitmq.order.command.routingKey}")
    private String orderCommandRoutingKey;

    @Value("${spring.rabbitmq.order.fail.command.exchange}")
    private String orderFailCommandExchange;

    @Value("${spring.rabbitmq.order.fail.command.routingKey}")
    private String orderFailCommandRoutingKey;

    @Value("${spring.rabbitmq.payment.command.exchange}")
    private String paymentCommandExchange;

    @Value("${spring.rabbitmq.payment.command.routingKey}")
    private String paymentCommandRoutingKey;

    @Value("${spring.rabbitmq.payment.fail.command.exchange}")
    private String paymentFailCommandExchange;

    @Value("${spring.rabbitmq.payment.fail.command.routingKey}")
    private String paymentFailCommandRoutingKey;

    @Value("${spring.rabbitmq.inventory.command.exchange}")
    private String inventoryCommandExchange;

    @Value("${spring.rabbitmq.inventory.command.routingKey}")
    private String inventoryCommandRoutingKey;

    @Value("${spring.rabbitmq.inventory.fail.command.exchange}")
    private String inventoryFailCommandExchange;

    @Value("${spring.rabbitmq.inventory.fail.command.routingKey}")
    private String inventoryFailCommandRoutingKey;

    @Override
    public void sendOrderCommand(OrderCommand orderCommand) {
        rabbitTemplate.convertAndSend(orderCommandExchange, orderCommandRoutingKey, orderCommand);
        log.info("Sent order command with sagaId: {}", orderCommand.sagaId());
    }

    @Override
    public void sendOrderFailCommand(FailCommand orderFailCommand) {
        rabbitTemplate.convertAndSend(orderFailCommandExchange, orderFailCommandRoutingKey, orderFailCommand);
        log.info("Sent order fail command with sagaId: {}", orderFailCommand.sagaId());
    }

    @Override
    public void sendPaymentCommand(PaymentCommand paymentCommand) {
        rabbitTemplate.convertAndSend(paymentCommandExchange, paymentCommandRoutingKey, paymentCommand);
        log.info("Sent payment command with sagaId: {}", paymentCommand.sagaId());
    }

    @Override
    public void sendPaymentFailCommand(FailCommand paymentFailCommand) {
        rabbitTemplate.convertAndSend(paymentFailCommandExchange, paymentFailCommandRoutingKey, paymentFailCommand);
        log.info("Sent payment fail command with sagaId: {}", paymentFailCommand.sagaId());
    }


    @Override
    public void sendInventoryCommand(InventoryCommand inventoryCommand) {
        rabbitTemplate.convertAndSend(inventoryCommandExchange, inventoryCommandRoutingKey, inventoryCommand);
        log.info("Sent inventory command with sagaId: {}", inventoryCommand.sagaId());
    }

    @Override
    public void sendInventoryFailCommand(FailCommand inventoryFailCommand) {
        rabbitTemplate.convertAndSend(inventoryFailCommandExchange, inventoryFailCommandRoutingKey, inventoryFailCommand);
        log.info("Sent inventory fail command with sagaId: {}", inventoryFailCommand.sagaId());
    }

}
