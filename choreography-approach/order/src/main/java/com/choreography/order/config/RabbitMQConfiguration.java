package com.choreography.order.config;

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

    @Value("${spring.rabbitmq.order.created.payment.queue}")
    private String orderCreatedPaymentQueue;

    @Value("${spring.rabbitmq.order.created.inventory.queue}")
    private String orderCreatedInventoryQueue;

    @Value("${spring.rabbitmq.order.created.exchange}")
    private String orderCreatedExchange;

    @Value("${spring.rabbitmq.order.created.routingKey}")
    private String orderCreatedRoutingKey;

    @Bean
    public Queue paymentQueue() {
        return new Queue(orderCreatedPaymentQueue);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(orderCreatedInventoryQueue);
    }

    @Bean
    public Exchange exchange() {
        return new FanoutExchange(orderCreatedExchange);
    }

    @Bean
    public Binding bindPaymentQueue() {
        return BindingBuilder.bind(paymentQueue()).to(exchange()).with(orderCreatedRoutingKey).noargs();
    }

    @Bean
    public Binding bindInventoryQueue() {
        return BindingBuilder.bind(inventoryQueue()).to(exchange()).with(orderCreatedRoutingKey).noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
