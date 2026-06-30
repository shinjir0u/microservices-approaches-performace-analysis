package com.choreography.payment.event;

import java.util.UUID;

public interface DomainEvent {

    String eventId();

    UUID orderId();

}
