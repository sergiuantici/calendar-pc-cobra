package com.example.calendar.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EventDTO {
    private Long id;
    private String name;
    private List<String> notes;
    public String getUsername() {
        return username;
    }

    public List<String> getNotes() {
        return notes;
    }

    public EventDTO setNotes(List<String> notes) {
        this.notes = notes;
        return this;
    }

    public EventDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public EventDTO setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public EventDTO setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public EventDTO setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    private String startTime;
    private String endTime;
    private String description;
    private String username;
    private LocalDateTime date;
    public EventDTO(Long id, String name, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
