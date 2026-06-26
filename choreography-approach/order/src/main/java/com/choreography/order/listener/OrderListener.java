package com.choreography.order.listener;

import com.choreography.order.events.inventory.InventoryReservedEvent;
import com.choreography.order.events.payment.PaymentChargedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderListener {

    @RabbitListener(queues = "${spring.rabbitmq.payment.charged.queue}")
    public void receivePayment(PaymentChargedEvent paymentChargedEvent) {
        log.info("Received paymentChargedEvent with id: {}", paymentChargedEvent.eventId());
    }

    @RabbitListener(queues = "${spring.rabbitmq.inventory.reserved.queue}")
    public void receiveInventoryTransaction(InventoryReservedEvent inventoryReservedEvent) {
        log.info("Received inventoryReservedEvent with id: {}", inventoryReservedEvent.eventId());
    }

}
