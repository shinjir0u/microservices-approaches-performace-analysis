package com.choreography.payment.listener;

import com.choreography.payment.events.order.OrderCreatedEvent;
import com.choreography.payment.model.processedEvent.ProcessedEvent;
import com.choreography.payment.model.processedEvent.type.EventStatus;
import com.choreography.payment.repository.ProcessedEventRepository;
import com.choreography.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final PaymentService paymentService;

    private final ProcessedEventRepository processedEventRepository;

    @RabbitListener(queues = "${spring.rabbitmq.order.created.payment.queue}")
    public void receiveOrder(OrderCreatedEvent orderCreatedEvent) {
        log.info("Received orderCreatedEvent with id: {}", orderCreatedEvent.eventId());
        paymentService.chargePayment(orderCreatedEvent.orderId(), orderCreatedEvent.totalAmount());

        ProcessedEvent processedEvent = ProcessedEvent
                .builder()
                .id(UUID.fromString(orderCreatedEvent.eventId()))
                .name(orderCreatedEvent.getClass().getName())
                .orderId(orderCreatedEvent.orderId())
                .status(EventStatus.SUCCESS)
                .processedAt(Instant.now())
                .build();
        ProcessedEvent savedProcessEvent = processedEventRepository.save(processedEvent);
        log.info("Processed orderCreatedEvent with order id: {}", savedProcessEvent.getOrderId());
    }

}
