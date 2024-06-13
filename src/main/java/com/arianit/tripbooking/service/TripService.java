package com.arianit.tripbooking.service;

import com.arianit.tripbooking.dao.TripRepository;
import com.arianit.tripbooking.dto.TripDto;
import com.arianit.tripbooking.dto.request.PageRequest;
import com.arianit.tripbooking.dto.request.TripRequest;
import com.arianit.tripbooking.dto.updateRequest.TripUpdateRequest;
import com.arianit.tripbooking.entity.Trip;
import com.arianit.tripbooking.exception.MismatchedInputException;
import com.arianit.tripbooking.exception.ResourceNotFoundException;
import com.arianit.tripbooking.mapper.TripMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final AuthenticationService authenticationService;

    @Transactional
    public TripDto create(TripRequest request){
        Trip trip = tripMapper.toEntity(request);
        trip.setCreatedBy(authenticationService.getLoggedInUser().getUsername());
        trip.setUserId(authenticationService.getLoggedInUser().getUserId());
        return tripMapper.toDto(tripRepository.save(trip));
    }

    public TripDto getById(Long id){
        Trip trip = tripRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Trip with %s id not found",id)
                ));
        return tripMapper.toDto(trip);
    }

    public List<TripDto> getAll(){
        List<Trip> trips = tripRepository.findAll();
        return trips.stream()
                .map(tripMapper::toDto)
                .collect(Collectors.toList());
    }
    public TripDto update (Long id, TripUpdateRequest request){
        if(!id.equals(request.getId())){
            throw new MismatchedInputException("Ids dosent matchs");
        }
        Trip tripIndDb = tripRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format("Trip with %s id not found",id))
        );
        tripMapper.toEntity(request,tripIndDb);
        return tripMapper.toDto(tripRepository.save(tripIndDb));

    }
    public void deleteById(Long id){
        Trip trip = tripRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Trip with %s id not found",id)
                ));
        tripRepository.deleteById(id);
    }

    public Page<TripDto> getAllPagable(@Valid PageRequest pageRequest){
        return tripRepository.findAll(pageRequest.getPageable()).map(
                tripMapper::toDto
        );
    }
}
