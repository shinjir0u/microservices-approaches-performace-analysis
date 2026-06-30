package com.choreography.inventory.event;

import java.util.UUID;

public interface DomainEvent {

    String eventId();

    UUID orderId();

}
