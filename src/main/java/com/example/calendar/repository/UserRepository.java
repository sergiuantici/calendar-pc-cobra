package com.example.calendar.repository;

import com.example.calendar.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Event, String> {
}
