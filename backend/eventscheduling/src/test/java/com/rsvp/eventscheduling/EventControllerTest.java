package com.rsvp.eventscheduling;


import com.rsvp.eventscheduling.controller.EventController;
import com.rsvp.eventscheduling.dto.CreateEventDTO;
import com.rsvp.eventscheduling.entity.Event;
import com.rsvp.eventscheduling.response.CreateEventResponse;
import com.rsvp.eventscheduling.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {
    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @Test
    public void createEvent_Test (){
        CreateEventDTO createEventDTO = new CreateEventDTO(1, "Mohan",new Date(2024 - 3 - 3), "1", "mohan", "Hyd" );
        CreateEventResponse createEventResponse = new CreateEventResponse("Mohan", "created event", HttpStatus.CREATED);

        when(eventService.addEvent(createEventDTO)).thenReturn(createEventResponse);

        ResponseEntity<?> response = eventController.createEvent(createEventDTO);

        verify(eventService, times(1)).addEvent(createEventDTO);
        assertEquals(createEventResponse, response.getBody() );
    }

    @Test
    public void viewEventsTest(){
        List<Event> events = new ArrayList<>();
        events.add(new Event(1,"Haldi", new Date(2024-3-3), "10:00", "Mohan", "Hyd"));

        when(eventService.fetchEvents()).thenReturn(events);

        List<Event> eventList = eventController.fetchUsers();

        verify(eventService, times(1)).fetchEvents();
        assertEquals(events.get(0), eventList.get(0));

    }

    @Test
    public void playEventTest(){

    }

}
