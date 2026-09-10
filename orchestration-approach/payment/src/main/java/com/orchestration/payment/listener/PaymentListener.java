package com.orchestration.payment.listener;

import com.orchestration.payment.config.RabbitMQSetting;
import com.orchestration.payment.model.command.PaymentCommand;
import com.orchestration.payment.service.rabbit.RabbitService;
import com.orchestration.payment.usecase.ChargePaymentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final ChargePaymentUseCase chargePaymentUseCase;

    private final RabbitService rabbitService;

    @RabbitListener(queues = RabbitMQSetting.ORCHESTRATOR_START_QUEUE)
    public void handlePaymentCommand(PaymentCommand paymentCommand) {
        log.info("Received paymentCommand with order id: {}", paymentCommand.orderId());

        try {
            chargePaymentUseCase.execute(paymentCommand);
            rabbitService.sendSagaReplyWithStatus(paymentCommand.sagaId(), paymentCommand.orderId(), true);
        } catch (IllegalArgumentException _) {
            rabbitService.sendSagaReplyWithStatus(paymentCommand.sagaId(), paymentCommand.orderId(), false);
        }
    }

}
