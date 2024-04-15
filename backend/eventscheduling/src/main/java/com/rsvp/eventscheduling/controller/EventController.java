package com.rsvp.eventscheduling.controller;

import com.rsvp.eventscheduling.dto.CreateEventDTO;
import com.rsvp.eventscheduling.dto.EventDTO;
import com.rsvp.eventscheduling.entity.Event;
import com.rsvp.eventscheduling.response.CreateEventResponse;
import com.rsvp.eventscheduling.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody CreateEventDTO createEventDTO){
         CreateEventResponse createEventResponse = eventService.addEvent(createEventDTO);
        return ResponseEntity.status(createEventResponse.getStatus()).body(createEventResponse);
    }

    @GetMapping(path = "/view")
    public List<String> fetchEvents(){
        return eventService.fetchEvents();
    }

    @GetMapping("/eventDetails/{eventId}")
    public Event  fetchEventDetails(@PathVariable int eventId){
        return eventService.fetchEventDetails(eventId);
    }

}
