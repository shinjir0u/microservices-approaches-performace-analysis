package com.orchestration.order.config;

public class RabbitMQSetting {

    public static final String ORCHESTRATOR_START_QUEUE = "${spring.rabbitmq.orchestrator.start.queue}";
    public static final String ORCHESTRATOR_START_EXCHANGE = "${spring.rabbitmq.orchestrator.start.exchange}";
    public static final String ORCHESTRATOR_START_ROUTING_KEY = "${spring.rabbitmq.orchestrator.start.routingKey}";

    public static final String ORCHESTRATOR_ORDER_REPLY_QUEUE = "${spring.rabbitmq.orchestrator.reply.queue}";
    public static final String ORCHESTRATOR_ORDER_REPLY_EXCHANGE = "${spring.rabbitmq.orchestrator.reply.exchange}";
    public static final String ORCHESTRATOR_ORDER_REPLY_ROUTING_KEY = "${spring.rabbitmq.orchestrator.reply.routingKey}";

    public static final String ORDER_COMMAND_QUEUE = "${spring.rabbitmq.order.command.queue}";

    public static final String ORDER_FAIL_COMMAND_QUEUE = "${spring.rabbitmq.order.fail.command.queue}";

}
