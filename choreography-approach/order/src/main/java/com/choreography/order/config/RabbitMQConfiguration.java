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

    @Value("${spring.rabbitmq.order.created.queue}")
    private String orderCreatedQueue;

    @Value("${spring.rabbitmq.order.created.exchange}")
    private String orderCreatedExchange;

    @Value("${spring.rabbitmq.order.created.routingKey}")
    private String orderCreatedRoutingKey;

    @Bean
    public Queue queue() {
        return new Queue(orderCreatedQueue);
    }

    @Bean
    public Exchange exchange() {
        return new TopicExchange(orderCreatedExchange);
    }

    @Bean
    public Binding bind(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(orderCreatedRoutingKey).noargs();
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
