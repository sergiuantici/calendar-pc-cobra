package com.example.calendar.controller;

import com.example.calendar.model.Event;
import com.example.calendar.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("events")
public class EventController {
    @Resource
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> save(Event event) {
        return new ResponseEntity<>(eventService.save(event), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllEvents() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }
}
