package com.rsvp.eventscheduling;

import com.rsvp.eventscheduling.dto.CreateEventDTO;
import com.rsvp.eventscheduling.entity.Event;
import com.rsvp.eventscheduling.repo.EventRepository;
import com.rsvp.eventscheduling.response.CreateEventResponse;
import com.rsvp.eventscheduling.service.EventService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void testAddEvents() {
        CreateEventDTO createEventDTO = new CreateEventDTO(1, "Haldi", new Date(2024 - 3 - 3), "12:00 PM", "Test Location", "Test Host"
        );
        when(eventRepository.save(any(Event.class))).thenReturn(new Event());

        CreateEventResponse response = eventService.addEvent(createEventDTO);

        verify(eventRepository, times(1)).save(any(Event.class));
        assertEquals("Haldi", createEventDTO.getEventName());
    }


    @Test
    public void testFetchEvents(){
        List<Event> events =new ArrayList<>();
        events.add(new Event(1,"Haldi", new Date(2024-3-3), "10:00", "Mohan", "Hyd"));

        when(eventRepository.findAll()).thenReturn(events);
        List<Event> fetchedEvents = eventService.fetchEvents();
        assertEquals(events.size(), fetchedEvents.size());
        assertEquals(events.get(0).getEventName(), fetchedEvents.get(0).getEventName());

        verify(eventRepository, times(1)).findAll();
    }

}
