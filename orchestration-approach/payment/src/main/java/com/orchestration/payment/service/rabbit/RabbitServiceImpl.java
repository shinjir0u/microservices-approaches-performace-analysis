package com.orchestration.payment.service.rabbit;

import com.orchestration.payment.model.payment.dto.SagaReply;
import com.orchestration.payment.model.payment.type.ServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitServiceImpl implements RabbitService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.orchestrator.reply.exchange}")
    private String orchestratorPaymentReplyExchange;

    @Value("${spring.rabbitmq.orchestrator.reply.routingKey}")
    private String orchestratorPaymentReplyRoutingKey;

    @Override
    public void sendSagaReplyWithStatus(UUID sagaId, UUID orderId, boolean success) {
        var sagaReply = SagaReply.builder()
                .sagaId(sagaId)
                .orderId(orderId)
                .serviceName(ServiceName.PAYMENT)
                .success(success)
                .build();

        rabbitTemplate.convertAndSend(
                orchestratorPaymentReplyExchange,
                orchestratorPaymentReplyRoutingKey,
                sagaReply
        );
    }

}
