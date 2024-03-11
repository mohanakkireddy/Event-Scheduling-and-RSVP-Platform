package com.rsvp.eventscheduling.response;

import org.springframework.http.HttpStatus;

public class CreateEventResponse {
    private String eventName;
    private String message;
    private HttpStatus status;

    public CreateEventResponse() {
    }

    public CreateEventResponse(String eventName, String message, HttpStatus status) {
        this.eventName = eventName;
        this.message = message;
        this.status = status;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CreateEventResponse{" +
                "eventName='" + eventName + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
