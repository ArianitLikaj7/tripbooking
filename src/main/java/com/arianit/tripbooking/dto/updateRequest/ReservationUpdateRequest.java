package com.arianit.tripbooking.dto.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUpdateRequest {
    private Long id;
    private Long tripId;
    private int  seatNumber;
}
