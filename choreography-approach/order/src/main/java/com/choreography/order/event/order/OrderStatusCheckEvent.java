package com.choreography.order.event.order;

import com.choreography.order.event.DomainEvent;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record OrderStatusCheckEvent(
        String eventId,
        UUID orderId
) implements DomainEvent {
}
