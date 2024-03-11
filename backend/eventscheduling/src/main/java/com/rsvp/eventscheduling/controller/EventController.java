package com.rsvp.eventscheduling.controller;

import com.rsvp.eventscheduling.dto.CreateEventDTO;
import com.rsvp.eventscheduling.entity.Event;
import com.rsvp.eventscheduling.response.CreateEventResponse;
import com.rsvp.eventscheduling.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    public List<Event> fetchUsers(){
        return eventService.fetchEvents();
    }


}
