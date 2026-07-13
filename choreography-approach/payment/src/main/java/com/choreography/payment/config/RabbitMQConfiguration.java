package com.choreography.payment.config;

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

    @Value("${spring.rabbitmq.payment.charged.queue}")
    private String paymentChargedQueue;

    @Value("${spring.rabbitmq.payment.charged.exchange}")
    private String paymentChargedExchange;

    @Value("${spring.rabbitmq.payment.charged.routingKey}")
    private String paymentChargedRoutingKey;

    @Value("${spring.rabbitmq.payment.failed.order.queue}")
    private String paymentFailedOrderQueue;

    @Value("${spring.rabbitmq.payment.failed.inventory.queue}")
    private String paymentFailedInventoryQueue;

    @Value("${spring.rabbitmq.payment.failed.exchange}")
    private String paymentFailedExchange;

    @Value("${spring.rabbitmq.payment.failed.routingKey}")
    private String paymentFailedRoutingKey;

    @Bean
    public Queue paymentChargedQueue() {
        return new Queue(paymentChargedQueue);
    }

    @Bean
    public Exchange paymentChargedExchange() {
        return new FanoutExchange(paymentChargedExchange);
    }

    @Bean
    public Binding bindPaymentChargedQueue() {
        return BindingBuilder.bind(paymentChargedQueue())
                .to(paymentChargedExchange()).with(paymentChargedRoutingKey).noargs();
    }

    @Bean
    public Queue paymentFailedOrderQueue() {
        return new Queue(paymentFailedOrderQueue);
    }

    @Bean
    public Queue paymentFailedInventoryQueue() {
        return new Queue(paymentFailedInventoryQueue);
    }

    @Bean
    public Exchange paymentFailedExchange() {
        return new FanoutExchange(paymentFailedExchange);
    }

    @Bean
    public Binding bindPaymentFailedOrderQueue() {
        return BindingBuilder.bind(paymentFailedOrderQueue()).to(paymentFailedExchange()).with(paymentFailedRoutingKey).noargs();
    }

    @Bean
    public Binding bindPaymentFailedInventoryQueue() {
        return BindingBuilder.bind(paymentFailedInventoryQueue()).to(paymentFailedExchange()).with(paymentFailedRoutingKey).noargs();
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
