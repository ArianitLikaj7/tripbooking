package com.arianit.tripbooking.mapper;

import com.arianit.tripbooking.dto.UserDto;
import com.arianit.tripbooking.dto.request.UserRequest;
import com.arianit.tripbooking.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class UserMapper implements GenericMapper<User, UserDto, UserRequest>{
    private final ModelMapper mapper;
    @Override
    public UserDto toDto(User entity) {
        return mapper.map(entity, UserDto.class);
    }

    @Override
    public User toEntity(UserRequest request) {
        return mapper.map(request, User.class);
    }
}
