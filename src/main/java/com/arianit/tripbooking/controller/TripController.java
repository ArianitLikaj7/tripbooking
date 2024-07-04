package com.arianit.tripbooking.controller;

import com.arianit.tripbooking.dto.TripDto;
import com.arianit.tripbooking.dto.request.PageRequest;
import com.arianit.tripbooking.dto.request.TripRequest;
import com.arianit.tripbooking.dto.updateRequest.TripUpdateRequest;
import com.arianit.tripbooking.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@Valid @RequestBody TripRequest request) {
        TripDto tripDto = tripService.create(request);
        return new ResponseEntity<>(tripDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable Long id) {
        TripDto tripDto = tripService.getById(id);
        return new ResponseEntity<>(tripDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TripDto>> getAllTrips() {
        List<TripDto> trips = tripService.getAll();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable Long id, @Valid @RequestBody TripUpdateRequest request) {
        TripDto updatedTrip = tripService.update(id, request);
        return new ResponseEntity<>(updatedTrip, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/pageable")
//    public ResponseEntity<Page<TripDto>> getTripsPaginated(@Valid PageRequest pageRequest) {
//        Page<TripDto> paginatedTrips = tripService.getAllPagable(pageRequest);
//        return new ResponseEntity<>(paginatedTrips, HttpStatus.OK);
//    }
}
