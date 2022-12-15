package com.example.calendar.service;

import com.example.calendar.Utils.DateUtils;
import com.example.calendar.notification.NotificationEvent;
import com.example.calendar.notification.NotificationEventType;
import com.example.calendar.model.Event;
import com.example.calendar.model.dto.EventDTO;
import com.example.calendar.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Resource
    ApplicationEventPublisher eventPublisher;

    private final EventRepository eventRepository;

    private final DateUtils dateUtils;

    @Autowired
    public EventService(EventRepository eventRepository, DateUtils dateUtils) {
        this.eventRepository = eventRepository;
        this.dateUtils = dateUtils;
    }

    public Event save(Event event) {
        eventPublisher.publishEvent(new NotificationEvent(event, NotificationEventType.NEW));
        return eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<EventDTO> findByMonthAndYear(Integer month, Integer year) {
        return eventRepository.getEventsByMonthAndYear(month, year);
    }

    public List<EventDTO> findByDayAndMonthAndYear(Integer day, Integer month, Integer year) {
        return  eventRepository.getEventsByDayAndMonthAndYear(day, month, year);
    }
    public Map<Integer, List<EventDTO>> findByMonthAndYearGroupedByDay(Integer month, Integer year){
        Map<Integer, List<EventDTO>> eventsPerDay = new HashMap<>();
        List<Integer> allDaysFromMonth = dateUtils.getAllDaysFromMonth(month, year);
        allDaysFromMonth.forEach( dayOfMonth -> {
            eventsPerDay.put(dayOfMonth, findByDayAndMonthAndYear(dayOfMonth, month, year));
        });
        return eventsPerDay;

    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> getUpcomingEvents() {
        List<Event> events = eventRepository.findAll();
        return events
                .stream()
                .filter(e -> e.getDate().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
}
