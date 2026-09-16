package com.orchestration.orchestrator.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${spring.rabbitmq.order.command.queue}")
    private String orderCommandQueue;

    @Value("${spring.rabbitmq.order.command.exchange}")
    private String orderCommandExchange;

    @Value("${spring.rabbitmq.order.command.routingKey}")
    private String orderCommandRoutingKey;

    @Value("${spring.rabbitmq.order.fail.command.queue}")
    private String orderFailCommandQueue;

    @Value("${spring.rabbitmq.order.fail.command.exchange}")
    private String orderFailCommandExchange;

    @Value("${spring.rabbitmq.order.fail.command.routingKey}")
    private String orderFailCommandRoutingKey;

    @Value("${spring.rabbitmq.payment.command.queue}")
    private String paymentCommandQueue;

    @Value("${spring.rabbitmq.payment.command.exchange}")
    private String paymentCommandExchange;

    @Value("${spring.rabbitmq.payment.command.routingKey}")
    private String paymentCommandRoutingKey;

    @Value("${spring.rabbitmq.payment.fail.command.queue}")
    private String paymentFailCommandQueue;

    @Value("${spring.rabbitmq.payment.fail.command.exchange}")
    private String paymentFailCommandExchange;

    @Value("${spring.rabbitmq.payment.fail.command.routingKey}")
    private String paymentFailCommandRoutingKey;

    @Value("${spring.rabbitmq.inventory.command.queue}")
    private String inventoryCommandQueue;

    @Value("${spring.rabbitmq.inventory.command.exchange}")
    private String inventoryCommandExchange;

    @Value("${spring.rabbitmq.inventory.command.routingKey}")
    private String inventoryCommandRoutingKey;

    @Value("${spring.rabbitmq.inventory.fail.command.queue}")
    private String inventoryFailCommandQueue;

    @Value("${spring.rabbitmq.inventory.fail.command.exchange}")
    private String inventoryFailCommandExchange;

    @Value("${spring.rabbitmq.inventory.fail.command.routingKey}")
    private String inventoryFailCommandRoutingKey;

    @Bean
    public Queue orderCommandQueue() {
        return new Queue(orderCommandQueue);
    }

    @Bean
    public Exchange orderCommandExchange() {
        return new DirectExchange(orderCommandExchange);
    }

    @Bean
    public Binding orderCommandBinding() {
        return BindingBuilder.bind(orderCommandQueue()).to(orderCommandExchange()).with(orderCommandRoutingKey).noargs();
    }

    @Bean
    public Queue orderFailCommandQueue() {
        return new Queue(orderFailCommandQueue);
    }

    @Bean
    public Exchange orderFailCommandExchange() {
        return new DirectExchange(orderFailCommandExchange);
    }

    @Bean
    public Binding orderFailCommandBinding() {
        return BindingBuilder.bind(orderFailCommandQueue()).to(orderFailCommandExchange()).with(orderFailCommandRoutingKey).noargs();
    }

    @Bean
    public Queue paymentCommandQueue() {
        return new Queue(paymentCommandQueue);
    }

    @Bean
    public Exchange paymentCommandExchange() {
        return new DirectExchange(paymentCommandExchange);
    }

    @Bean
    public Binding paymentCommandBinding() {
        return BindingBuilder.bind(paymentCommandQueue()).to(paymentCommandExchange()).with(paymentCommandRoutingKey).noargs();
    }

    @Bean
    public Queue paymentFailCommandQueue() {
        return new Queue(paymentFailCommandQueue);
    }

    @Bean
    public Exchange paymentFailCommandExchange() {
        return new DirectExchange(paymentFailCommandExchange);
    }

    @Bean
    public Binding paymentFailCommandBinding() {
        return BindingBuilder.bind(paymentFailCommandQueue()).to(paymentFailCommandExchange()).with(paymentFailCommandRoutingKey).noargs();
    }

    @Bean
    public Queue inventoryCommandQueue() {
        return new Queue(inventoryCommandQueue);
    }

    @Bean
    public Exchange inventoryCommandExchange() {
        return new DirectExchange(inventoryCommandExchange);
    }

    @Bean
    public Binding inventoryCommandBinding() {
        return BindingBuilder.bind(inventoryCommandQueue()).to(inventoryCommandExchange()).with(inventoryCommandRoutingKey).noargs();
    }

    @Bean
    public Queue inventoryFailCommandQueue() {
        return new Queue(inventoryFailCommandQueue);
    }

    @Bean
    public Exchange inventoryFailCommandExchange() {
        return new DirectExchange(inventoryFailCommandExchange);
    }

    @Bean
    public Binding inventoryFailCommandBinding() {
        return BindingBuilder.bind(inventoryFailCommandQueue()).to(inventoryFailCommandExchange()).with(inventoryFailCommandRoutingKey).noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        rabbitTemplate.setObservationEnabled(true);
        return rabbitTemplate;
    }

}
