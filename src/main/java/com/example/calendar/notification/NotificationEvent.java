package com.example.calendar.notification;

import com.example.calendar.model.Event;
import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {
    private Event event;
    private NotificationEventType type;

    public NotificationEvent(Event event, NotificationEventType type) {
        super(event);
        this.event = event;
        this.type = type;
    }

    public Event getEvent() {
        return event;
    }

    public NotificationEventType getType() {
        return type;
    }
}
