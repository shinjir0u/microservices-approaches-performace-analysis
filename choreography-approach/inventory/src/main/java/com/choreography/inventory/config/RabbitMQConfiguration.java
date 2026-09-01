package com.choreography.inventory.config;

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

    @Value("${spring.rabbitmq.inventory.reserved.queue}")
    private String inventoryReservedQueue;

    @Value("${spring.rabbitmq.inventory.reserved.exchange}")
    private String inventoryReservedExchange;

    @Value("${spring.rabbitmq.inventory.reserved.routingKey}")
    private String inventoryReservedRoutingKey;

    @Value("${spring.rabbitmq.inventory.failed.order.queue}")
    private String inventoryFailedOrderQueue;

    @Value("${spring.rabbitmq.inventory.failed.payment.queue}")
    private String inventoryFailedPaymentQueue;

    @Value("${spring.rabbitmq.inventory.failed.exchange}")
    private String inventoryFailedExchange;

    @Value("${spring.rabbitmq.inventory.failed.routingKey}")
    private String inventoryFailedRoutingKey;

    @Bean
    public Queue inventoryReservedQueue() {
        return new Queue(inventoryReservedQueue);
    }

    @Bean
    public Exchange inventoryReservedExchange() {
        return new FanoutExchange(inventoryReservedExchange);
    }

    @Bean
    public Binding bindInventoryReservedQueue() {
        return BindingBuilder.bind(inventoryReservedQueue()).to(inventoryReservedExchange()).with(inventoryReservedRoutingKey).noargs();
    }

    @Bean
    public Queue inventoryFailedOrderQueue() {
        return new Queue(inventoryFailedOrderQueue);
    }

    @Bean
    public Queue inventoryFailedPaymentQueue() {
        return new Queue(inventoryFailedPaymentQueue);
    }

    @Bean
    public Exchange inventoryFailedExchange() {
        return new FanoutExchange(inventoryFailedExchange);
    }

    @Bean
    public Binding bindInventoryFailedOrderQueue() {
        return BindingBuilder.bind(inventoryFailedOrderQueue()).to(inventoryFailedExchange()).with(inventoryFailedRoutingKey).noargs();
    }

    @Bean
    public Binding bindInventoryFailedPaymentQueue() {
        return BindingBuilder.bind(inventoryFailedPaymentQueue()).to(inventoryFailedExchange()).with(inventoryFailedRoutingKey).noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        rabbitTemplate.setObservationEnabled(true);
        return rabbitTemplate;
    }

}
