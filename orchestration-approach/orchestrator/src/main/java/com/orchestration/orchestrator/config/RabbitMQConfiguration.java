package com.orchestration.orchestrator.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue orderCommandQueue() {
        return new Queue(RabbitMQSetting.ORDER_COMMAND_QUEUE);
    }

    @Bean
    public Exchange orderCommandExchange() {
        return new DirectExchange(RabbitMQSetting.ORDER_COMMAND_EXCHANGE);
    }

    @Bean
    public Binding orderCommandBinding() {
        return BindingBuilder.bind(orderCommandQueue()).to(orderCommandExchange()).with(RabbitMQSetting.ORDER_COMMAND_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue orderFailCommandQueue() {
        return new Queue(RabbitMQSetting.ORDER_FAIL_COMMAND_QUEUE);
    }

    @Bean
    public Exchange orderFailCommandExchange() {
        return new DirectExchange(RabbitMQSetting.ORDER_FAIL_COMMAND_EXCHANGE);
    }

    @Bean
    public Binding orderFailCommandBinding() {
        return BindingBuilder.bind(orderFailCommandQueue()).to(orderFailCommandExchange()).with(RabbitMQSetting.ORDER_FAIL_COMMAND_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue paymentCommandQueue() {
        return new Queue(RabbitMQSetting.PAYMENT_COMMAND_QUEUE);
    }

    @Bean
    public Exchange paymentCommandExchange() {
        return new DirectExchange(RabbitMQSetting.PAYMENT_COMMAND_EXCHANGE);
    }

    @Bean
    public Binding paymentCommandBinding() {
        return BindingBuilder.bind(paymentCommandQueue()).to(paymentCommandExchange()).with(RabbitMQSetting.PAYMENT_COMMAND_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue inventoryCommandQueue() {
        return new Queue(RabbitMQSetting.INVENTORY_COMMAND_QUEUE);
    }

    @Bean
    public Exchange inventoryCommandExchange() {
        return new DirectExchange(RabbitMQSetting.INVENTORY_COMMAND_EXCHANGE);
    }

    @Bean
    public Binding inventoryCommandBinding() {
        return BindingBuilder.bind(inventoryCommandQueue()).to(inventoryCommandExchange()).with(RabbitMQSetting.INVENTORY_COMMAND_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue paymentFailCommandQueue() {
        return new Queue(RabbitMQSetting.PAYMENT_FAIL_COMMAND_QUEUE);
    }

    @Bean
    public Exchange paymentFailCommandExchange() {
        return new DirectExchange(RabbitMQSetting.PAYMENT_FAIL_COMMAND_EXCHANGE);
    }

    @Bean
    public Binding paymentFailCommandBinding() {
        return BindingBuilder.bind(paymentFailCommandQueue()).to(paymentFailCommandExchange()).with(RabbitMQSetting.PAYMENT_FAIL_COMMAND_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue inventoryFailCommandQueue() {
        return new Queue(RabbitMQSetting.INVENTORY_FAIL_COMMAND_QUEUE);
    }

    @Bean
    public Exchange inventoryFailCommandExchange() {
        return new DirectExchange(RabbitMQSetting.INVENTORY_FAIL_COMMAND_EXCHANGE);
    }

    @Bean
    public Binding inventoryFailCommandBinding() {
        return BindingBuilder.bind(inventoryFailCommandQueue()).to(inventoryFailCommandExchange()).with(RabbitMQSetting.INVENTORY_FAIL_COMMAND_ROUTING_KEY).noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
