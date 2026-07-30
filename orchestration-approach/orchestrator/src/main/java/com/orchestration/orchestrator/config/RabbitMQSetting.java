package com.orchestration.orchestrator.config;

public class RabbitMQSetting {

    public static final String ORCHESTRATOR_START_QUEUE = "orchestrator.start.queue";
    public static final String ORCHESTRATOR_START_EXCHANGE = "orchestrator.start.exchange";
    public static final String ORCHESTRATOR_START_ROUTING_KEY = "orchestrator.start.routingkey";

    public static final String ORCHESTRATOR_REPLY_QUEUE = "orchestrator.reply.queue";
    public static final String ORCHESTRATOR_REPLY_EXCHANGE = "orchestrator.reply.exchange";
    public static final String ORCHESTRATOR_REPLY_ROUTING_KEY = "orchestrator.reply.routingkey";

    public static final String INVENTORY_COMMAND_QUEUE = "inventory.command.queue";
    public static final String INVENTORY_COMMAND_EXCHANGE = "inventory.command.exchange";
    public static final String INVENTORY_COMMAND_ROUTING_KEY = "inventory.command.routingkey";

    public static final String PAYMENT_COMMAND_QUEUE = "payment.command.queue";
    public static final String PAYMENT_COMMAND_EXCHANGE = "payment.command.exchange";
    public static final String PAYMENT_COMMAND_ROUTING_KEY = "payment.command.routingkey";

    public static final String ORDER_COMMAND_QUEUE = "order.command.queue";
    public static final String ORDER_COMMAND_EXCHANGE = "order.command.exchange";
    public static final String ORDER_COMMAND_ROUTING_KEY = "order.command.routingkey";

}
