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
public class ProcessSuccessDomainEventUseCase {

    private final ProcessedEventService processedEventService;

    private final OrderService orderService;

    @Transactional
    public void execute(DomainEvent domainEvent) {
        boolean eventProcessed = processedEventService.existsByEventId(UUID.fromString(domainEvent.eventId()));
        if (eventProcessed)
            return;

        processedEventService.saveDomainEvent(domainEvent, EventStatus.SUCCESS);
        orderService.processReceivedDomainEvent(domainEvent.orderId());
    }

}
