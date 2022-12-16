package com.example.calendar.service.email;

import com.example.calendar.model.Event;

public interface NotificationService {

    void sendNewEventNotification(Event event);

    void sendReminder(Event event);
}
