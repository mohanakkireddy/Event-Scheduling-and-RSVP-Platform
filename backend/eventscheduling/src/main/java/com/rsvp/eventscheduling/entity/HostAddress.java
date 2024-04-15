package com.rsvp.eventscheduling.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "host_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostAddress {
    @Id
    @GeneratedValue
    private int addressId;
    @NonNull
    private String streetName;
    @NonNull
    private String city;
    @NonNull
    private String state;
    @NonNull
    private int zip;

}
