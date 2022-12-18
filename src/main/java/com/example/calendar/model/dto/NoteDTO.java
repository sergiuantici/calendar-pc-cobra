package com.example.calendar.model.dto;

public class NoteDTO {
    Long idEvent;
    String username;
    String note;

    public NoteDTO(Long idEvent, String username, String note) {
        this.idEvent = idEvent;
        this.username = username;
        this.note = note;
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public NoteDTO setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public NoteDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNote() {
        return note;
    }

    public NoteDTO setNote(String note) {
        this.note = note;
        return this;
    }
}
