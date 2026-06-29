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

    @Bean
    public Queue queue() {
        return new Queue(inventoryReservedQueue);
    }

    @Bean
    public Exchange exchange() {
        return new FanoutExchange(inventoryReservedExchange);
    }

    @Bean
    public Binding bind(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(inventoryReservedRoutingKey).noargs();
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
