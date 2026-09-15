package com.orchestration.payment.usecase;

import com.orchestration.payment.model.command.FailCommand;
import com.orchestration.payment.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RevertPaymentUseCase {

    private final PaymentService paymentService;

    public void execute(FailCommand paymentCommand) {
        paymentService.revertPayment(paymentCommand.orderId());
    }
}
