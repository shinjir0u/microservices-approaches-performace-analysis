package com.orchestration.payment.config;

public class RabbitMQSetting {

    public static final String ORCHESTRATOR_START_QUEUE = "orchestrator.start.queue";

    public static final String ORCHESTRATOR_REPLY_QUEUE = "orchestrator.reply.queue";
    public static final String ORCHESTRATOR_REPLY_EXCHANGE = "orchestrator.reply.exchange";
    public static final String ORCHESTRATOR_REPLY_ROUTING_KEY = "orchestrator.reply.routingkey";

    public static final String PAYMENT_COMMAND_QUEUE = "payment.command.queue";

    public static final String PAYMENT_FAIL_COMMAND_QUEUE = "payment.fail.command.queue";

}
