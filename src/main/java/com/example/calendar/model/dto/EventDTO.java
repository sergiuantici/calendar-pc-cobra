package com.example.calendar.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

public class EventDTO {
    private Long id;
    private String name;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private List<String> notes;

    public String getUsername() {
        return username;
    }

    public EventDTO(Long id, String name, List<String> notes, String startTime, String endTime, String description, String username, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.username = username;
        this.date = date;
    }

    public EventDTO() {
    }

    public EventDTO(Long id, String name, String startTime, String endTime, String description, String username, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.username = username;
        this.date = date;
    }

    private String startTime;
    private String endTime;
    private String description;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String username;
    private LocalDateTime date;
    private Double latitude;
    private Double longitude;
    public EventDTO(Long id, String name, LocalDateTime date,String startTime,String endTime,String description,Double latitude,Double longitude) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.startTime=startTime;
        this.endTime=endTime;
        this.description=description;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public List<String> getNotes() {
        return notes;
    }

    public EventDTO setNotes(List<String> notes) {
        this.notes = notes;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
