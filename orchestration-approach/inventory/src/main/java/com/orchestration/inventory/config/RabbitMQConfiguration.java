package com.orchestration.inventory.config;

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

    @Value("${spring.rabbitmq.orchestrator.reply.queue}")
    private String orchestratorInventoryReplyQueue;

    @Value("${spring.rabbitmq.orchestrator.reply.exchange}")
    private String orchestratorInventoryReplyExchange;

    @Value("${spring.rabbitmq.orchestrator.reply.routingKey}")
    private String orchestratorInventoryReplyRoutingKey;

    @Bean
    public Queue orchestratorReplyQueue() {
        return new Queue(orchestratorInventoryReplyQueue);
    }

    @Bean
    public Exchange orchestratorReplyExchange() {
        return new DirectExchange(orchestratorInventoryReplyExchange);
    }

    @Bean
    public Binding orchestratorReplyBinding() {
        return BindingBuilder.bind(orchestratorReplyQueue()).to(orchestratorReplyExchange()).with(orchestratorInventoryReplyRoutingKey).noargs();
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
