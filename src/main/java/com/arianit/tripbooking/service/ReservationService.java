package com.arianit.tripbooking.service;

import com.arianit.tripbooking.dao.ReservationRepository;
import com.arianit.tripbooking.dto.ReservationDto;
import com.arianit.tripbooking.dto.TripDto;
import com.arianit.tripbooking.dto.request.ReservationRequest;
import com.arianit.tripbooking.dto.updateRequest.ReservationUpdateRequest;
import com.arianit.tripbooking.entity.Reservation;
import com.arianit.tripbooking.entity.Trip;
import com.arianit.tripbooking.exception.IlegalNumberOfSeatsException;
import com.arianit.tripbooking.exception.MismatchedInputException;
import com.arianit.tripbooking.exception.ResourceNotFoundException;
import com.arianit.tripbooking.mapper.ReservationMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final AuthenticationService authenticationService;
    private final TripService tripService;

    @Transactional
    public ReservationDto create (ReservationRequest request){
        validateSeatAvailability(request.getTripId(), request.getSeatNumber());
        Reservation reservation = reservationMapper.toEntity(request);
        reservation.setCreatedBy(authenticationService.getLoggedInUser().getUsername());
        reservation.setUserId(authenticationService.getLoggedInUser().getUserId());
        mapTripToReservation(request,reservation);
        updateAvailableSeats(request.getTripId(), request.getSeatNumber());
        Reservation reservationInDb = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservationInDb);
    }

    public ReservationDto getById(Long id){
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Reservation with %s id not found",id)
                ));
        return reservationMapper.toDto(reservationInDb);
    }

    public List<ReservationDto> getAll(){
        List<Reservation> trips = reservationRepository.findAll();
        return trips.stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public ReservationDto update (Long id, ReservationUpdateRequest request){
        if(!id.equals(request.getId())){
            throw new MismatchedInputException("Ids dosent matchs");
        }
        Reservation reservationIndDb = reservationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format("Reservation with %s id not found",id))
        );
        reservationMapper.toEntity(request,reservationIndDb);
        return reservationMapper.toDto(reservationRepository.save(reservationIndDb));

    }

    public void delete (Long id) {
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format("Reservation with %s id not found",id)
                ));
        reservationRepository.deleteById(id);
    }

    private void updateAvailableSeats(Long tripId, int i) {
        Trip trip = tripService.getEntityById(tripId);
        int availableSeats = trip.getAvailableSeats() + i;
        if (availableSeats < 0){
            throw new IlegalNumberOfSeatsException("No available seats left.");
        }
        trip.setAvailableSeats(availableSeats);
        tripService.saveEntity(trip);
    }

    private void validateSeatAvailability(Long tripId, int seatNumber) {
        TripDto tripDto = tripService.getById(tripId);
        if(seatNumber > tripDto.getAvailableSeats() || seatNumber < 1){
            throw new IlegalNumberOfSeatsException
                    ("Seat number: " +seatNumber + " is not available. Available seats: "+ tripDto.getAvailableSeats());
        }
    }

    private void mapTripToReservation(ReservationRequest request, Reservation reservation) {
        TripDto tripDto = tripService.getById(request.getTripId());
        reservation.setTripId(tripDto.getId());
    }
    private void mapUpdateTripToReservation(ReservationRequest request, Reservation reservation) {
        if (request.getTripId()!= null) {
            TripDto tripDto = tripService.getById(request.getTripId());
            reservation.setTripId(tripDto.getId());
        }
    }
}
