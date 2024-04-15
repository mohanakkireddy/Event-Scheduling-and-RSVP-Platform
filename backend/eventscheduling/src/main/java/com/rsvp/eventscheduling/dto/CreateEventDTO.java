package com.rsvp.eventscheduling.dto;

import com.rsvp.eventscheduling.entity.HostAddress;
import lombok.Data;

import java.util.Date;

@Data
public class CreateEventDTO {
    private int eventId;
    private String eventName;
    private Date eventDate;
    private String eventTime;
    private String hostName;
    private String location;
    private HostAddress hostAddress;


}
