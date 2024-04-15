package com.rsvp.eventscheduling.dto;

public class EventDTO {
    private String eventName;

    public EventDTO() {
    }

    public EventDTO(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "eventName='" + eventName + '\'' +
                '}';
    }
}
