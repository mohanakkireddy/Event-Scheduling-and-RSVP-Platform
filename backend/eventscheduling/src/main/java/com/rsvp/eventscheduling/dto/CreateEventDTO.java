package com.rsvp.eventscheduling.dto;

import java.util.Date;

public class CreateEventDTO {
    private int eventId;
    private String eventName;
    private Date eventDate;
    private String eventTime;
    private String hostName;
    private String location;

    public CreateEventDTO() {
    }

    public CreateEventDTO(int eventId, String eventName, Date eventDate, String eventTime, String hostName, String location) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.hostName = hostName;
        this.location = location;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CreateEventDTO{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", eventTime='" + eventTime + '\'' +
                ", hostName='" + hostName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
