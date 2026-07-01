package com.choreography.inventory.service.processedEvent;

import com.choreography.inventory.event.DomainEvent;
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
    public void saveProcessedEvent(DomainEvent event, EventStatus eventStatus) {
        ProcessedEvent processedEvent = ProcessedEvent
                .builder()
                .eventId(UUID.fromString(event.eventId()))
                .name(event.getClass().getSimpleName())
                .orderId(event.orderId())
                .status(eventStatus)
                .processedAt(Instant.now())
                .build();
        ProcessedEvent savedProcessEvent = processedEventRepository.save(processedEvent);
        log.info("Processed {} with order id: {}", event.getClass().getSimpleName(), savedProcessEvent.getOrderId());
    }

}
