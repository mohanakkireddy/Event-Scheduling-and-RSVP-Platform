package com.rsvp.eventscheduling.service;

import com.rsvp.eventscheduling.dto.CreateEventDTO;
import com.rsvp.eventscheduling.entity.Event;
import com.rsvp.eventscheduling.entity.HostAddress;
import com.rsvp.eventscheduling.repo.EventRepository;
import com.rsvp.eventscheduling.response.CreateEventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                createEventDTO.getHostName(),
                createEventDTO.getHostAddress()
        );


        eventRepository.save(event);
        return new CreateEventResponse(event.getEventName(), "Event created successfully", HttpStatus.CREATED);
    }

    public List<String> fetchEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(Event::getEventName)
                .collect(Collectors.toList());

    }


    public Event fetchEventDetails(int eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        return(event.get());
    }


}
