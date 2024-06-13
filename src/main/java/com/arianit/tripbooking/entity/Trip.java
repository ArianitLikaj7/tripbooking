package com.arianit.tripbooking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class Trip extends BaseEntity{

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @Column(name = "available_seats")
    private int availableSeats;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "route")
    private String route;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "trip")
    @JsonManagedReference
    private List<Reservation> reservations;


}
