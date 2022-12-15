package com.example.calendar.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "username", nullable = false)
    private String username;
    private String password;

    @OneToMany(mappedBy="user")
    private List<Event> events;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public List<Event> getEvents() {
        return events;
    }

    public User setEvents(List<Event> events) {
        this.events = events;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
