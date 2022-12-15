package com.example.calendar.controller;

import com.example.calendar.model.dto.EventDTO;
import com.example.calendar.model.dto.NoteDTO;
import com.example.calendar.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;


@RestController
@RequestMapping("events")
@CrossOrigin(origins = "*")

public class EventController {
    @Resource
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody EventDTO eventDto) {
        boolean real = eventService.verifyUser(eventDto);
        if (!real)
            return new ResponseEntity<>("Not correct username", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(eventService.save(eventDto), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody EventDTO eventDto) {
        boolean real = eventService.verifyUser(eventDto);
        if (!real)
            return new ResponseEntity<>("Not correct username", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(eventService.update(eventDto), HttpStatus.OK);
    }

    @PutMapping("/note")
    public ResponseEntity<?> addNote(@RequestBody NoteDTO noteDto) {
        if (!eventService.verifyCorrect(noteDto))
            return new ResponseEntity<>("Incorrect Event and ID", HttpStatus.FORBIDDEN);
        if ("".equals(noteDto.getNote()) || noteDto.getNote() == null)
            return new ResponseEntity<>("You need to have string", HttpStatus.BAD_REQUEST);
        String note = eventService.addNote(noteDto);
        if (Objects.equals(note, ""))
            return new ResponseEntity<>("Event doesnt exist", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    @GetMapping("/by-month-year")
    public ResponseEntity<?> getAllByMonthAndYear(@RequestParam("month") Integer month, @RequestParam("year") Integer year) {
        return new ResponseEntity<>(eventService.findByMonthAndYearGroupedByDay(month, year), HttpStatus.OK);
    }

    @GetMapping("/by-day")
    public ResponseEntity<?> getAllByDayAndMonthAndYear(@RequestParam("day") Integer day, @RequestParam("month") Integer month, @RequestParam("year") Integer year) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(eventService.findByDayAndMonthAndYear(day, month, year), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);

    }



    @GetMapping
    public ResponseEntity<?> findAllEvents() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<?> findAllByUser(@PathVariable("username") String username) {
        return new ResponseEntity<>(eventService.findAllByUser(username), HttpStatus.OK);

    }
}
