package com.example.calendar.service;

import com.example.calendar.Utils.DateUtils;
import com.example.calendar.model.Event;
import com.example.calendar.model.dto.EventDTO;
import com.example.calendar.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService {
    private final EventRepository eventRepository;

    private final DateUtils dateUtils;

    @Autowired
    public EventService(EventRepository eventRepository, DateUtils dateUtils) {
        this.eventRepository = eventRepository;
        this.dateUtils = dateUtils;
    }


    public Event save(Event event) {
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

}
