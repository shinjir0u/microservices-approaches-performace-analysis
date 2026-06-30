package com.choreography.order.listener;

import com.choreography.order.event.inventory.InventoryReservedEvent;
import com.choreography.order.event.payment.PaymentChargedEvent;
import com.choreography.order.model.processedEvent.type.EventStatus;
import com.choreography.order.service.processedEvent.ProcessedEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final ProcessedEventService processedEventService;

    @Transactional
    @RabbitListener(queues = "${spring.rabbitmq.payment.charged.queue}")
    public void receivePayment(PaymentChargedEvent paymentChargedEvent) {
        log.info("Received paymentChargedEvent with id: {}", paymentChargedEvent.eventId());
        processedEventService.saveAppEvent(paymentChargedEvent, EventStatus.SUCCESS);
    }

    @Transactional
    @RabbitListener(queues = "${spring.rabbitmq.inventory.reserved.queue}")
    public void receiveInventoryTransaction(InventoryReservedEvent inventoryReservedEvent) {
        log.info("Received inventoryReservedEvent with id: {}", inventoryReservedEvent.eventId());
        processedEventService.saveAppEvent(inventoryReservedEvent, EventStatus.SUCCESS);
    }

}
