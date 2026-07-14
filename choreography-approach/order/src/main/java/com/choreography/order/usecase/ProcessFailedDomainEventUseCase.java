package com.choreography.order.usecase;

import com.choreography.order.event.DomainEvent;
import com.choreography.order.model.processedEvent.type.EventStatus;
import com.choreography.order.service.order.OrderService;
import com.choreography.order.service.processedEvent.ProcessedEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessFailedDomainEventUseCase {

    private final OrderService orderService;

    private final ProcessedEventService processedEventService;

    @Transactional
    public void execute(DomainEvent domainEvent) {
        String eventName = domainEvent.getClass().getSimpleName();

        boolean eventProcessed = processedEventService.existsByEventId(UUID.fromString(domainEvent.eventId()));
        if (eventProcessed) {
            log.info("{} with id: {} is already processed.", eventName, domainEvent.eventId());
            return;
        }

        orderService.processFailedDomainEvent(domainEvent.orderId(), eventName);
        processedEventService.saveDomainEvent(domainEvent, EventStatus.SUCCESS);
    }

}
