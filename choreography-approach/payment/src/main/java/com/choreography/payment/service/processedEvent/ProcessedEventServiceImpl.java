package com.choreography.payment.service.processedEvent;

import com.choreography.payment.events.order.OrderCreatedEvent;
import com.choreography.payment.model.processedEvent.ProcessedEvent;
import com.choreography.payment.model.processedEvent.type.EventStatus;
import com.choreography.payment.repository.ProcessedEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessedEventServiceImpl implements ProcessedEventService {

    private final ProcessedEventRepository processedEventRepository;

    @Override
    @Transactional
    public void saveProcessedEvent(OrderCreatedEvent orderCreatedEvent, EventStatus eventStatus) {
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
