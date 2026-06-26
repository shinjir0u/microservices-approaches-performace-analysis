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

    @Bean
    public Queue paymentChargedQueue() {
        return new Queue(paymentChargedQueue);
    }

    @Bean
    public Exchange paymentChargedExchange() {
        return new TopicExchange(paymentChargedExchange);
    }

    @Bean
    public Binding bind() {
        return BindingBuilder.bind(paymentChargedQueue())
                .to(paymentChargedExchange()).with(paymentChargedRoutingKey).noargs();
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
