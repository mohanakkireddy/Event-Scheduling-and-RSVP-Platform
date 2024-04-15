package com.rsvp.eventscheduling.repo;

import com.rsvp.eventscheduling.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
//    @Query(value = "SELECT * FROM events e Where e.event_id=:eventId ", nativeQuery = true)
//    Event findEventDetails(int eventId);
}
