package com.orchestration.inventory.config;

public class RabbitMQSetting {

    public static final String ORCHESTRATOR_START_QUEUE = "orchestrator.start.queue";
    public static final String ORCHESTRATOR_START_EXCHANGE = "orchestrator.start.exchange";
    public static final String ORCHESTRATOR_START_ROUTING_KEY = "orchestrator.start.routingkey";

    public static final String ORCHESTRATOR_REPLY_QUEUE = "orchestrator.reply.queue";
    public static final String ORCHESTRATOR_REPLY_EXCHANGE = "orchestrator.reply.exchange";
    public static final String ORCHESTRATOR_REPLY_ROUTING_KEY = "orchestrator.reply.routingkey";

    public static final String INVENTORY_COMMAND_QUEUE = "inventory.command.queue";

}
