package com.arianit.tripbooking.dto;


import com.arianit.tripbooking.entity.Role;

public record AuthenticationResponse(String token, String refreshToken, Role role) {
}
