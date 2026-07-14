package com.choreography.order.listener;

import com.choreography.order.event.inventory.InventoryFailedEvent;
import com.choreography.order.event.inventory.InventoryReservedEvent;
import com.choreography.order.event.payment.PaymentChargedEvent;
import com.choreography.order.event.payment.PaymentFailedEvent;
import com.choreography.order.usecase.ProcessFailedDomainEventUseCase;
import com.choreography.order.usecase.ProcessSucceededDomainEventUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final ProcessSucceededDomainEventUseCase processSucceededDomainEventUseCase;

    private final ProcessFailedDomainEventUseCase processFailedDomainEventUseCase;

    @RabbitListener(queues = "${spring.rabbitmq.payment.charged.queue}")
    public void receivePayment(PaymentChargedEvent paymentChargedEvent) {
        log.info("Received paymentChargedEvent with id: {}", paymentChargedEvent.eventId());
        processSucceededDomainEventUseCase.execute(paymentChargedEvent);
    }

    @RabbitListener(queues = "${spring.rabbitmq.inventory.reserved.queue}")
    public void receiveInventoryTransaction(InventoryReservedEvent inventoryReservedEvent) {
        log.info("Received inventoryReservedEvent with id: {}", inventoryReservedEvent.eventId());
        processSucceededDomainEventUseCase.execute(inventoryReservedEvent);
    }

    @RabbitListener(queues = "${spring.rabbitmq.payment.failed.order.queue}")
    public void receiveFailedPayment(PaymentFailedEvent paymentFailedEvent) {
        log.info("Received paymentFailedEvent with id: {}", paymentFailedEvent.eventId());
        processFailedDomainEventUseCase.execute(paymentFailedEvent);
    }

    @RabbitListener(queues = "${spring.rabbitmq.inventory.failed.order.queue}")
    public void receiveFailedInventoryTransaction(InventoryFailedEvent inventoryFailedEvent) {
        log.info("Received inventoryFailedEvent with id: {}", inventoryFailedEvent.eventId());
        processFailedDomainEventUseCase.execute(inventoryFailedEvent);
    }
}
