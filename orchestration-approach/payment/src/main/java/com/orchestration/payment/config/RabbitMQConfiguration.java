package com.orchestration.payment.config;

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
    private String orchestratorPaymentReplyQueue;

    @Value("${spring.rabbitmq.orchestrator.reply.exchange}")
    private String orchestratorPaymentReplyExchange;

    @Value("${spring.rabbitmq.orchestrator.reply.routingKey}")
    private String orchestratorPaymentReplyRoutingKey;

    @Bean
    public Queue orchestratorReplyQueue() {
        return new Queue(orchestratorPaymentReplyQueue);
    }

    @Bean
    public Exchange orchestratorReplyExchange() {
        return new DirectExchange(orchestratorPaymentReplyExchange);
    }

    @Bean
    public Binding orchestratorReplyBinding() {
        return BindingBuilder.bind(orchestratorReplyQueue()).to(orchestratorReplyExchange()).with(orchestratorPaymentReplyRoutingKey).noargs();
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
        return rabbitTemplate;
    }

}
