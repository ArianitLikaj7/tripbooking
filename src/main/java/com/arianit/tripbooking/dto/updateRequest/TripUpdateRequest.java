package com.arianit.tripbooking.dto.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripUpdateRequest {
    private Long userId;
    private String origin;
    private String destination;
    private int availableSeats;
    private int totalSeats;
    private String route;
}
