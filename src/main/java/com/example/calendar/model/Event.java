package com.example.calendar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    @JsonBackReference
    private User user;

    public User getUser() {
        return user;
    }

    public Event setUser(User user) {
        this.user = user;
        return this;
    }

    @ElementCollection
    private List<String> notes = new ArrayList<>();
    public Event() {

    }
    public String getEndTime() {
        return endTime;
    }

    public void addNote(String note){
        notes.add(note);
    }
    public Event setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public Event setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public List<String> getNotes() {
        return notes;
    }

    public Event setNotes(List<String> notes) {
        this.notes = notes;
        return this;
    }

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Event(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
