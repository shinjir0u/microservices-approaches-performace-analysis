package com.orchestration.order.config;

public class RabbitMQSetting {

    public static final String ORCHESTRATOR_START_QUEUE = "orchestrator.start.queue";
    public static final String ORCHESTRATOR_START_EXCHANGE = "orchestrator.start.exchange";
    public static final String ORCHESTRATOR_START_ROUTING_KEY = "orchestrator.start.routingkey";

    public static final String ORCHESTRATOR_ORDER_REPLY_QUEUE = "orchestrator.order.reply.queue";
    public static final String ORCHESTRATOR_ORDER_REPLY_EXCHANGE = "orchestrator.order.reply.exchange";
    public static final String ORCHESTRATOR_ORDER_REPLY_ROUTING_KEY = "orchestrator.order.reply.routingkey";

    public static final String ORDER_COMMAND_QUEUE = "order.command.queue";

    public static final String ORDER_FAIL_COMMAND_QUEUE = "order.fail.command.queue";

}
