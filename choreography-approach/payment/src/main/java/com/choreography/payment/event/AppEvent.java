package com.choreography.payment.event;

import java.util.UUID;

public interface AppEvent {

    String eventId();

    UUID orderId();

}
