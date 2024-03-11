package com.rsvp.eventscheduling.service;

import com.rsvp.eventscheduling.dto.CreateEventDTO;
import com.rsvp.eventscheduling.entity.Event;
import com.rsvp.eventscheduling.repo.EventRepository;
import com.rsvp.eventscheduling.response.CreateEventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    public CreateEventResponse addEvent(CreateEventDTO createEventDTO) {
        Event event = new Event(
                createEventDTO.getEventId(),
                createEventDTO.getEventName(),
                createEventDTO.getEventDate(),
                createEventDTO.getEventTime(),
                createEventDTO.getLocation(),
                createEventDTO.getHostName()
        );
        eventRepository.save(event);
        return new CreateEventResponse(event.getEventName(), "Event created successfully", HttpStatus.CREATED);
    }

    public List<Event> fetchEvents() {
        return (List<Event>)
                eventRepository.findAll();
    }
}
