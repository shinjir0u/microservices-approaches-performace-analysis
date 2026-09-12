package com.orchestration.orchestrator.config;

public class RabbitMQSetting {

    public static final String ORCHESTRATOR_START_QUEUE = "orchestrator.start.queue";

    public static final String ORCHESTRATOR_INVENTORY_REPLY_QUEUE = "orchestrator.inventory.reply.queue";

    public static final String ORCHESTRATOR_PAYMENT_REPLY_QUEUE = "orchestrator.payment.reply.queue";

    public static final String INVENTORY_COMMAND_QUEUE = "inventory.command.queue";
    public static final String INVENTORY_COMMAND_EXCHANGE = "inventory.command.exchange";
    public static final String INVENTORY_COMMAND_ROUTING_KEY = "inventory.command.routingkey";

    public static final String PAYMENT_COMMAND_QUEUE = "payment.command.queue";
    public static final String PAYMENT_COMMAND_EXCHANGE = "payment.command.exchange";
    public static final String PAYMENT_COMMAND_ROUTING_KEY = "payment.command.routingkey";

    public static final String INVENTORY_FAIL_COMMAND_QUEUE = "inventory.fail.command.queue";
    public static final String INVENTORY_FAIL_COMMAND_EXCHANGE = "inventory.fail.command.exchange";
    public static final String INVENTORY_FAIL_COMMAND_ROUTING_KEY = "inventory.fail.command.routingkey";

    public static final String PAYMENT_FAIL_COMMAND_QUEUE = "payment.fail.command.queue";
    public static final String PAYMENT_FAIL_COMMAND_EXCHANGE = "payment.fail.command.exchange";
    public static final String PAYMENT_FAIL_COMMAND_ROUTING_KEY = "payment.fail.command.routingkey";

    public static final String ORDER_COMMAND_QUEUE = "order.command.queue";
    public static final String ORDER_COMMAND_EXCHANGE = "order.command.exchange";
    public static final String ORDER_COMMAND_ROUTING_KEY = "order.command.routingkey";

    public static final String ORDER_FAIL_COMMAND_QUEUE = "order.fail.command.queue";
    public static final String ORDER_FAIL_COMMAND_EXCHANGE = "order.fail.command.exchange";
    public static final String ORDER_FAIL_COMMAND_ROUTING_KEY = "order.fail.command.routingkey";

}
