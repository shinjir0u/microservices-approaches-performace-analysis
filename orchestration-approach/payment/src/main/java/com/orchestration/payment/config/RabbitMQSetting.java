package com.orchestration.payment.config;

public class RabbitMQSetting {

    public static final String ORCHESTRATOR_PAYMENT_REPLY_QUEUE = "orchestrator.payment.reply.queue";
    public static final String ORCHESTRATOR_PAYMENT_REPLY_EXCHANGE = "orchestrator.payment.reply.exchange";
    public static final String ORCHESTRATOR_PAYMENT_REPLY_ROUTING_KEY = "orchestrator.payment.reply.routingkey";

    public static final String PAYMENT_COMMAND_QUEUE = "payment.command.queue";

    public static final String PAYMENT_FAIL_COMMAND_QUEUE = "payment.fail.command.queue";

}
