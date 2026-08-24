package com.choreography.order.usecase;

import com.choreography.order.event.DomainEvent;
import com.choreography.order.model.processedEvent.type.EventStatus;
import com.choreography.order.service.processedEvent.ProcessedEventService;
import com.choreography.order.service.rabbit.RabbitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessSucceededDomainEventUseCase {

    private final ProcessedEventService processedEventService;

    private final RabbitService rabbitService;

    @Transactional
    public void execute(DomainEvent domainEvent) {
        boolean eventProcessed = processedEventService.existsByEventId(UUID.fromString(domainEvent.eventId()));
        if (eventProcessed) {
            log.info("{} with id: {} is already processed.", domainEvent.getClass().getSimpleName(), domainEvent.eventId());
            return;
        }

        processedEventService.saveDomainEvent(domainEvent, EventStatus.SUCCESS);
        log.info("We processing event with order id {} to update status to success", domainEvent.eventId());
        rabbitService.publishOrderStatusCheckEvent(domainEvent.orderId());
    }

}
