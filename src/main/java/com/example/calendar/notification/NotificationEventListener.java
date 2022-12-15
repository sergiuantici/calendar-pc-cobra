package com.example.calendar.notification;

import com.example.calendar.model.Event;
import com.example.calendar.service.email.NotificationService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class NotificationEventListener implements ApplicationListener<NotificationEvent> {

    @Resource
    private NotificationService notificationService;

    @Override
    public void onApplicationEvent(NotificationEvent notificationEvent) {
        Event event = notificationEvent.getEvent();
        NotificationEventType notificationEventType = notificationEvent.getType();

        switch (notificationEventType) {
            case NEW -> notificationService.sendNewEventNotification(event);
            case REMINDER -> notificationService.sendReminder(event);
        }
    }
}
