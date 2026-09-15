package com.orchestration.payment.listener;

import com.orchestration.payment.model.command.FailCommand;
import com.orchestration.payment.model.command.PaymentCommand;
import com.orchestration.payment.service.rabbit.RabbitService;
import com.orchestration.payment.usecase.ChargePaymentUseCase;
import com.orchestration.payment.usecase.RevertPaymentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final ChargePaymentUseCase chargePaymentUseCase;

    private final RevertPaymentUseCase revertPaymentUseCase;

    private final RabbitService rabbitService;

    @RabbitListener(queues = "${spring.rabbitmq.payment.command.queue}")
    public void handlePaymentCommand(PaymentCommand paymentCommand) {
        log.info("Received paymentCommand with order id: {}", paymentCommand.orderId());

        try {
            chargePaymentUseCase.execute(paymentCommand);
            rabbitService.sendSagaReplyWithStatus(paymentCommand.sagaId(), paymentCommand.orderId(), true);
        } catch (IllegalArgumentException _) {
            rabbitService.sendSagaReplyWithStatus(paymentCommand.sagaId(), paymentCommand.orderId(), false);
        }
    }

    @RabbitListener(queues = "${spring.rabbitmq.payment.fail.command.queue}")
    public void handlePaymentFailCommand(FailCommand paymentFailCommand) {
        log.info("Received paymentFailCommand with order id: {}", paymentFailCommand.orderId());

        revertPaymentUseCase.execute(paymentFailCommand);
    }

}
