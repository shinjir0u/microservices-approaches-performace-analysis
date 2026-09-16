package com.orchestration.order.config;

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

    @Value("${spring.rabbitmq.orchestrator.start.queue}")
    private String orchestratorStartQueue;

    @Value("${spring.rabbitmq.orchestrator.start.exchange}")
    private String orchestratorStartExchange;

    @Value("${spring.rabbitmq.orchestrator.start.routingKey}")
    private String orchestratorStartRoutingKey;

    @Value("${spring.rabbitmq.orchestrator.reply.queue}")
    private String orchestratorOrderReplyQueue;

    @Value("${spring.rabbitmq.orchestrator.reply.exchange}")
    private String orchestratorOrderReplyExchange;

    @Value("${spring.rabbitmq.orchestrator.reply.routingKey}")
    private String orchestratorOrderReplyRoutingKey;

    @Bean
    public Queue orchestratorStartQueue() {
        return new Queue(orchestratorStartQueue);
    }

    @Bean
    public Exchange orchestratorStartExchange() {
        return new DirectExchange(orchestratorStartExchange);
    }

    @Bean
    public Binding orchestratorStartBinding() {
        return BindingBuilder.bind(orchestratorStartQueue()).to(orchestratorStartExchange()).with(orchestratorStartRoutingKey).noargs();
    }

    @Bean
    public Queue orchestratorReplyQueue() {
        return new Queue(orchestratorOrderReplyQueue);
    }

    @Bean
    public Exchange orchestratorReplyExchange() {
        return new DirectExchange(orchestratorOrderReplyExchange);
    }

    @Bean
    public Binding orchestratorReplyBinding() {
        return BindingBuilder.bind(orchestratorReplyQueue()).to(orchestratorReplyExchange()).with(orchestratorOrderReplyRoutingKey).noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        var rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        rabbitTemplate.setObservationEnabled(true);
        return rabbitTemplate;
    }

}
