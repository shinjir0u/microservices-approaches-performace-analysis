package com.choreography.order.listener;

import com.choreography.order.event.inventory.InventoryReservedEvent;
import com.choreography.order.event.payment.PaymentChargedEvent;
import com.choreography.order.usecase.ProcessSucceededDomainEventUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final ProcessSucceededDomainEventUseCase processSucceededDomainEventUseCase;

    @Transactional
    @RabbitListener(queues = "${spring.rabbitmq.payment.charged.queue}")
    public void receivePayment(PaymentChargedEvent paymentChargedEvent) {
        log.info("Received paymentChargedEvent with id: {}", paymentChargedEvent.eventId());
        processSucceededDomainEventUseCase.execute(paymentChargedEvent);
    }

    @Transactional
    @RabbitListener(queues = "${spring.rabbitmq.inventory.reserved.queue}")
    public void receiveInventoryTransaction(InventoryReservedEvent inventoryReservedEvent) {
        log.info("Received inventoryReservedEvent with id: {}", inventoryReservedEvent.eventId());
        processSucceededDomainEventUseCase.execute(inventoryReservedEvent);
    }

}
