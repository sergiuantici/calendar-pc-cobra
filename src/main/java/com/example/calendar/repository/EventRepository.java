package com.example.calendar.repository;

import com.example.calendar.model.Event;
import com.example.calendar.model.dto.EventDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


    @Query("select new com.example.calendar.model.dto.EventDTO(e.id, e.name, e.date) " +
            "from Event e " +
            "where year(e.date) = :year " +
            "and month(e.date) = :month " +
            "order by e.date " )
    List<EventDTO> getEventsByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);

    @Query("select new com.example.calendar.model.dto.EventDTO(e.id, e.name, e.date) " +
            "from Event e " +
            "where year(e.date) = :year " +
            "and month(e.date) = :month " +
            "and day(e.date) = :day " +
            "order by e.date ")
    List<EventDTO> getEventsByDayAndMonthAndYear(@Param("day") Integer day, @Param("month") Integer month, @Param("year") Integer year);

}
