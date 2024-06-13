package com.arianit.tripbooking.mapper;

import com.arianit.tripbooking.dto.TripDto;
import com.arianit.tripbooking.dto.request.TripRequest;
import com.arianit.tripbooking.dto.updateRequest.TripUpdateRequest;
import com.arianit.tripbooking.entity.Trip;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripMapper
        implements UpdateGenericMapper<Trip, TripDto, TripRequest, TripUpdateRequest>{

    private final ModelMapper mapper;
    @Override
    public TripDto toDto(Trip entity) {
        return mapper.map(entity, TripDto.class);
    }

    @Override
    public Trip toEntity(TripRequest request) {
        return mapper.map(request, Trip.class);
    }

    @Override
    public void toEntity(TripUpdateRequest updateRequest, Trip entity) {
       mapper.map(updateRequest,entity);
    }
}
