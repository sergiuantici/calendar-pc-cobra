package com.example.calendar.service;

import com.example.calendar.Utils.DateUtils;
import com.example.calendar.model.Event;
import com.example.calendar.model.User;
import com.example.calendar.model.dto.EventDTO;
import com.example.calendar.model.dto.NoteDTO;
import com.example.calendar.repository.EventRepository;
import com.example.calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final DateUtils dateUtils;

    @Autowired
    public EventService(EventRepository eventRepository, UserRepository userRepository, DateUtils dateUtils) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.dateUtils = dateUtils;
    }


    public Event save(EventDTO eventDto) {
        Event event = fromDtoToModel(eventDto);
        return eventRepository.save(event);

    }

    private Event fromDtoToModel(EventDTO eventDto) {
        Event event = new Event();
        event.setUser(new User(eventDto.getUsername()));
        event.setEndTime(eventDto.getEndTime());
        event.setStartTime(eventDto.getStartTime());
        event.setNotes(eventDto.getNotes());
        event.setDate(eventDto.getDate());
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        return event;
    }
    private Event fromDtoToModel2(EventDTO eventDto) {
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setUser(new User(eventDto.getUsername()));
        event.setEndTime(eventDto.getEndTime());
        event.setStartTime(eventDto.getStartTime());
        event.setNotes(eventDto.getNotes());
        event.setDate(eventDto.getDate());
        event.setName(eventDto.getName());
        event.setDescription(eventDto.getDescription());
        return event;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public List<EventDTO> findByMonthAndYear(Integer month, Integer year) {
        return eventRepository.getEventsByMonthAndYear(month, year);
    }

    public List<EventDTO> findByDayAndMonthAndYear(Integer day, Integer month, Integer year) {
        return eventRepository.getEventsByDayAndMonthAndYear(day, month, year);
    }

    public Map<Integer, List<EventDTO>> findByMonthAndYearGroupedByDay(Integer month, Integer year) {
        Map<Integer, List<EventDTO>> eventsPerDay = new HashMap<>();
        List<Integer> allDaysFromMonth = dateUtils.getAllDaysFromMonth(month, year);
        allDaysFromMonth.forEach(dayOfMonth -> {
            eventsPerDay.put(dayOfMonth, findByDayAndMonthAndYear(dayOfMonth, month, year));
        });
        return eventsPerDay;

    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public boolean verifyUser(EventDTO eventDto) {

        return userRepository.existsByUsername(eventDto.getUsername());
    }

    public String addNote(NoteDTO noteDto) {
        Optional<Event> byId = eventRepository.findById(noteDto.getIdEvent());
        if (byId.isPresent()) {
            var event = byId.get();
            event.addNote(noteDto.getNote());
            eventRepository.save(event);
            return noteDto.getNote();
        }
        return "";
    }

    public boolean verifyCorrect(NoteDTO noteDto) {
        User user = new User(noteDto.getUsername());
        return eventRepository.existsByIdAndUser(noteDto.getIdEvent(), user);
    }

    public ArrayList<Event> findAllByUser(String username) {
        return eventRepository.findByUser(new User(username));
    }

    public Event update(EventDTO eventDto) {
        Event event = fromDtoToModel2(eventDto);
        return eventRepository.save(event);

    }
}
