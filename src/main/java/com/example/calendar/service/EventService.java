package com.example.calendar.service;

import com.example.calendar.model.Event;
import com.example.calendar.repository.EventRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EventService {
    @Resource
    private EventRepository eventRepository;

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

}
