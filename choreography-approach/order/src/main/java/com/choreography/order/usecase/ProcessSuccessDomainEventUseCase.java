package com.choreography.order.usecase;

import com.choreography.order.event.DomainEvent;
import com.choreography.order.model.processedEvent.type.EventStatus;
import com.choreography.order.service.order.OrderService;
import com.choreography.order.service.processedEvent.ProcessedEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessSuccessDomainEventUseCase {

    private final ProcessedEventService processedEventService;

    private final OrderService orderService;

    public void execute(DomainEvent domainEvent) {
        processedEventService.saveDomainEvent(domainEvent, EventStatus.SUCCESS);
        orderService.processReceivedDomainEvent(domainEvent.orderId());
    }

}
