package com.example.calendar.controller;

import com.example.calendar.model.Event;
import com.example.calendar.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("events")
public class EventController {
    @Resource
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Event event) {
        return new ResponseEntity<>(eventService.save(event), HttpStatus.OK);
    }
    @GetMapping("/by-month-year")
    public ResponseEntity<?> getAllByMonthAndYear(@RequestParam("month") Integer month, @RequestParam("year") Integer year){
       return new ResponseEntity<>(eventService.findByMonthAndYearGroupedByDay(month, year), HttpStatus.OK);
    }

    @GetMapping("/by-day")
    public ResponseEntity<?> getAllByDayAndMonthAndYear(@RequestParam("day") Integer day, @RequestParam("month") Integer month, @RequestParam("year") Integer year){
        return new ResponseEntity<>(eventService.findByDayAndMonthAndYear(day, month, year), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> findAllEvents() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }
}
