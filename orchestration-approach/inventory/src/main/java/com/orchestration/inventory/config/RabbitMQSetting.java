package com.orchestration.inventory.config;

public class RabbitMQSetting {

    public static final String ORCHESTRATOR_REPLY_QUEUE = "orchestrator.reply.queue";
    public static final String ORCHESTRATOR_REPLY_EXCHANGE = "orchestrator.reply.exchange";
    public static final String ORCHESTRATOR_REPLY_ROUTING_KEY = "orchestrator.reply.routingkey";

    public static final String INVENTORY_COMMAND_QUEUE = "inventory.command.queue";

    public static final String INVENTORY_FAIL_COMMAND_QUEUE = "inventory.fail.command.queue";

}
