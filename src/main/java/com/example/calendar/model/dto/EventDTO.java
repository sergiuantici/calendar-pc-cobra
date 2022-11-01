package com.example.calendar.model.dto;

import java.time.LocalDateTime;

public class EventDTO {
    private Long id;
    private String name;

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
