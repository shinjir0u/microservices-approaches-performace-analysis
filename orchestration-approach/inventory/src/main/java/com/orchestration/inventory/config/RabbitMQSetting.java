package com.orchestration.inventory.config;

public class RabbitMQSetting {

    public static final String ORCHESTRATOR_INVENTORY_REPLY_QUEUE = "${spring.rabbitmq.orchestrator.reply.queue}";
    public static final String ORCHESTRATOR_INVENTORY_REPLY_EXCHANGE = "${spring.rabbitmq.orchestrator.reply.exchange}";
    public static final String ORCHESTRATOR_INVENTORY_REPLY_ROUTING_KEY = "${spring.rabbitmq.orchestrator.reply.routingKey}";

    public static final String INVENTORY_COMMAND_QUEUE = "${spring.rabbitmq.inventory.command.queue}";

    public static final String INVENTORY_FAIL_COMMAND_QUEUE = "${spring.rabbitmq.inventory.fail.command.queue}";

}
