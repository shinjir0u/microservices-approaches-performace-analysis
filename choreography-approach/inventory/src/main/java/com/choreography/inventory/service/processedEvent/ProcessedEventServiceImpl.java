package com.choreography.inventory.service.processedEvent;

import com.choreography.inventory.events.order.OrderCreatedEvent;
import com.choreography.inventory.model.processedEvent.ProcessedEvent;
import com.choreography.inventory.model.processedEvent.type.EventStatus;
import com.choreography.inventory.repository.ProcessedEventRepository;
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
                .status(eventStatus)
                .processedAt(Instant.now())
                .build();
        ProcessedEvent savedProcessEvent = processedEventRepository.save(processedEvent);
        log.info("Processed orderCreatedEvent with order id: {}", savedProcessEvent.getOrderId());
    }

}
