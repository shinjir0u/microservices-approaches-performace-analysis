package com.choreography.order.event;

import java.util.UUID;

public interface DomainEvent {

    String eventId();

    UUID orderId();

}
