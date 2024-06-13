package com.arianit.tripbooking.controller;

import com.arianit.tripbooking.dto.ReservationDto;
import com.arianit.tripbooking.dto.request.ReservationRequest;
import com.arianit.tripbooking.dto.updateRequest.ReservationUpdateRequest;
import com.arianit.tripbooking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody ReservationRequest request) {
        ReservationDto reservationDto = reservationService.create(request);
        return new ResponseEntity<>(reservationDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        ReservationDto reservationDto = reservationService.getById(id);
        return new ResponseEntity<>(reservationDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationUpdateRequest request) {
        ReservationDto updatedReservation = reservationService.update(id, request);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
