package com.arianit.tripbooking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripRequest {
    private String origin;
    private String destination;
    private int availableSeats;
    private int totalSeats;
    private String route;
    private String typeOfTrip;
}
