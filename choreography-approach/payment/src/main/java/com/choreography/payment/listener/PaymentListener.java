package com.choreography.payment.listener;

import com.choreography.payment.event.inventory.InventoryFailedEvent;
import com.choreography.payment.event.order.OrderCreatedEvent;
import com.choreography.payment.model.payment.Payment;
import com.choreography.payment.service.rabbit.RabbitService;
import com.choreography.payment.usecase.ChargePaymentUseCase;
import com.choreography.payment.usecase.RevertPaymentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final RabbitService rabbitService;

    private final ChargePaymentUseCase chargePaymentUseCase;

    private final RevertPaymentUseCase revertPaymentUseCase;

    @RabbitListener(queues = "${spring.rabbitmq.order.created.payment.queue}")
    public void receiveOrder(OrderCreatedEvent orderCreatedEvent) {
        log.info("Received orderCreatedEvent with id: {}", orderCreatedEvent.eventId());

        try {
            Payment payment = chargePaymentUseCase.execute(orderCreatedEvent);
            rabbitService.publishPaymentChargedEvent(payment.getId(), payment.getOrderId());
        } catch (IllegalArgumentException exception) {
            rabbitService.publishPaymentFailedEvent(orderCreatedEvent.orderId(), "Invalid amount.");
        }
    }

    @RabbitListener(queues = "${spring.rabbitmq.inventory.failed.payment.queue}")
    public void revertPayment(InventoryFailedEvent inventoryFailedEvent) {
        log.info("Received InventoryFailedEvent with id: {}", inventoryFailedEvent.eventId());

        revertPaymentUseCase.execute(inventoryFailedEvent);
    }

}
