package com.arianit.tripbooking.mapper;

import com.arianit.tripbooking.dto.ReservationDto;
import com.arianit.tripbooking.dto.request.ReservationRequest;
import com.arianit.tripbooking.dto.updateRequest.ReservationUpdateRequest;
import com.arianit.tripbooking.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper implements UpdateGenericMapper
        <Reservation, ReservationDto, ReservationRequest, ReservationUpdateRequest>{

    private final ModelMapper mapper;

    @Override
    public ReservationDto toDto(Reservation entity) {
        return mapper.map(entity, ReservationDto.class);
    }

    @Override
    public Reservation toEntity(ReservationRequest request) {
        return mapper.map(request, Reservation.class);
    }

    @Override
    public void toEntity(ReservationUpdateRequest updateRequest, Reservation entity) {
        mapper.map(updateRequest,entity);
    }
}
