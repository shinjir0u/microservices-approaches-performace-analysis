package com.choreography.order.listener;

import com.choreography.order.events.inventory.InventoryReservedEvent;
import com.choreography.order.events.payment.PaymentChargedEvent;
import com.choreography.order.model.processedEvent.ProcessedEvent;
import com.choreography.order.model.processedEvent.type.EventStatus;
import com.choreography.order.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final ProcessedEventRepository processedEventRepository;

    @RabbitListener(queues = "${spring.rabbitmq.payment.charged.queue}")
    public void receivePayment(PaymentChargedEvent paymentChargedEvent) {
        log.info("Received paymentChargedEvent with id: {}", paymentChargedEvent.eventId());

        ProcessedEvent processedEvent = ProcessedEvent
                .builder().id(UUID.fromString(paymentChargedEvent.eventId()))
                .name(paymentChargedEvent.getClass().getName())
                .orderId(paymentChargedEvent.orderId())
                .status(EventStatus.SUCCESS)
                .processedAt(Instant.now())
                .build();
        ProcessedEvent savedProcessEvent = processedEventRepository.save(processedEvent);
        log.info("Processed paymentChargedEvent with order id: {}", savedProcessEvent.getOrderId());
    }

    @RabbitListener(queues = "${spring.rabbitmq.inventory.reserved.queue}")
    public void receiveInventoryTransaction(InventoryReservedEvent inventoryReservedEvent) {
        log.info("Received inventoryReservedEvent with id: {}", inventoryReservedEvent.eventId());

        ProcessedEvent processedEvent = ProcessedEvent
                .builder().id(UUID.fromString(inventoryReservedEvent.eventId()))
                .name(inventoryReservedEvent.getClass().getName())
                .orderId(inventoryReservedEvent.orderId())
                .status(EventStatus.SUCCESS)
                .processedAt(Instant.now())
                .build();
        ProcessedEvent savedProcessEvent = processedEventRepository.save(processedEvent);
        log.info("Processed inventoryReservedEvent with order id: {}", savedProcessEvent.getOrderId());
    }

}
