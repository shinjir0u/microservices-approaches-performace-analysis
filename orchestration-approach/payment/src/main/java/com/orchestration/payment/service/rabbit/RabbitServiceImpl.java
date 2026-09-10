package com.orchestration.payment.service.rabbit;

import com.orchestration.payment.config.RabbitMQSetting;
import com.orchestration.payment.model.payment.dto.SagaReply;
import com.orchestration.payment.model.payment.type.ServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitServiceImpl implements RabbitService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendSagaReplyWithStatus(UUID sagaId, UUID orderId, boolean success) {
        var sagaReply = SagaReply.builder()
                .sagaId(sagaId)
                .orderId(orderId)
                .serviceName(ServiceName.PAYMENT)
                .success(success)
                .build();

        rabbitTemplate.convertAndSend(
                RabbitMQSetting.ORCHESTRATOR_REPLY_EXCHANGE,
                RabbitMQSetting.ORCHESTRATOR_REPLY_ROUTING_KEY,
                sagaReply
        );
    }

}
