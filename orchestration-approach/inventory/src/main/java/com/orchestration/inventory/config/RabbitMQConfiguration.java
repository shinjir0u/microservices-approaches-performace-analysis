package com.orchestration.inventory.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue orchestratorStartQueue() {
        return new Queue(RabbitMQSetting.ORCHESTRATOR_START_QUEUE);
    }

    @Bean
    public Exchange orchestratorStartExchange() {
        return new DirectExchange(RabbitMQSetting.ORCHESTRATOR_START_EXCHANGE);
    }

    @Bean
    public Binding orchestratorStartBinding() {
        return BindingBuilder.bind(orchestratorStartQueue()).to(orchestratorStartExchange()).with(RabbitMQSetting.ORCHESTRATOR_START_ROUTING_KEY).noargs();
    }

    @Bean
    public Queue orchestratorReplyQueue() {
        return new Queue(RabbitMQSetting.ORCHESTRATOR_REPLY_QUEUE);
    }

    @Bean
    public Exchange orchestratorReplyExchange() {
        return new DirectExchange(RabbitMQSetting.ORCHESTRATOR_REPLY_EXCHANGE);
    }

    @Bean
    public Binding orchestratorReplyBinding() {
        return BindingBuilder.bind(orchestratorReplyQueue()).to(orchestratorReplyExchange()).with(RabbitMQSetting.ORCHESTRATOR_REPLY_ROUTING_KEY).noargs();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        var rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
