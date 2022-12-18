package com.example.calendar.service.email.impl;

import com.example.calendar.Utils.DateUtils;
import com.example.calendar.model.Event;
import com.example.calendar.notification.NotificationEvent;
import com.example.calendar.notification.NotificationEventType;
import com.example.calendar.service.EventService;
import com.example.calendar.service.email.EmailService;
import com.example.calendar.service.email.NotificationService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DefaultNotificationService implements NotificationService {
    @Resource
    ApplicationEventPublisher eventPublisher;

    @Resource
    private EmailService emailService;

    @Resource
    private EventService eventService;

    @Resource
    private DateUtils dateUtils;

    private final String DATE_TIME_PATTERN = "yyyy-MM-dd";
    @Override
    public void sendNewEventNotification(Event event) {
        String subject = "You have a new event";
        String text = "A new event was created. \n";
        text = text + event.getName() + " event was created.\n";
        text = text + event.getDescription() + "\n";
        text = text + "Date of the event: " + dateUtils.getStringDate(event.getDate(), DATE_TIME_PATTERN) + "\n";
//        String to = "amos.andreica@gmail.com";
        String to = event.getUser().getUsername();

        emailService.sendSimpleMessage(to, subject, text);
    }

    @Override
    public void sendReminder(Event event) {
        String subject = "You have an upcoming event";
        String text = "You have an upcoming event. \n";
        text = text + event.getName() + " event is coming.\n";
        text = text + event.getDescription() + "\n";
        text = text + "Date of the event: " + event.getDate() + "\n";
        String to = "amos.andreica@gmail.com";
        //String to = event.getEvent().getEmail();

        emailService.sendSimpleMessage(to, subject, text);
    }

    @Async
    @Scheduled(cron = "0 47 18 * * ?")
    public void sendRemindersTask() {
        List<Event> upcomingEvents = eventService.getUpcomingEvents();
        upcomingEvents
                .forEach(e -> eventPublisher.publishEvent(new NotificationEvent(e, NotificationEventType.REMINDER)));
    }
}
