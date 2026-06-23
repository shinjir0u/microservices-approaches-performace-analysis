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
    private String orderQueue;

    @Value("${spring.rabbitmq.order.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.order.routingKey}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(orderQueue);
    }

    @Bean
    public Exchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding bind(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
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
