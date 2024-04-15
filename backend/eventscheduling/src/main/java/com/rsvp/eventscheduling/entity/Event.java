package com.rsvp.eventscheduling.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private int eventId;
    @NonNull
    private String eventName;
    @NonNull
    private Date eventDate;
    @NonNull
    private String eventTime;
    @NonNull
    private String hostName;
    @NonNull
    private String location;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id_fk")
    private HostAddress hostAddress;


}
