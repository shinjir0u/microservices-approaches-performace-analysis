package com.choreography.order.event;

import java.util.UUID;

public interface AppEvent {

    String eventId();

    UUID orderId();

}
