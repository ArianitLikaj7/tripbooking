package com.arianit.tripbooking.service;

import com.arianit.tripbooking.config.JwtService;
import com.arianit.tripbooking.dao.UserRepository;
import com.arianit.tripbooking.dto.AuthenticationResponse;
import com.arianit.tripbooking.dto.CurrentLoggedInUserDto;
import com.arianit.tripbooking.dto.request.AuthenticationRequest;
import com.arianit.tripbooking.dto.request.RefreshTokenRequest;
import com.arianit.tripbooking.dto.request.RegisterRequest;
import com.arianit.tripbooking.entity.Role;
import com.arianit.tripbooking.entity.User;
import com.arianit.tripbooking.exception.TokenRefreshException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomUserDetailService customUserDetailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = customUserDetailService.loadUserByUsername(request.username());
        return new AuthenticationResponse(jwtService.generateToken(user),
                jwtService.generateRefreshToken(user),user.getRole());
    }

    public void register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request){
        String requestRefreshToken = request.token();

        User user = customUserDetailService.
                loadUserByUsername(jwtService.extractUsername(requestRefreshToken));

        if (!jwtService.isTokenValid(requestRefreshToken, user)){
            throw new TokenRefreshException("Refresh token was expired. Please make a new sign-in request");
        }

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token, requestRefreshToken,user.getRole());
    }


    public CurrentLoggedInUserDto getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();


        return CurrentLoggedInUserDto.builder()
                .userId(loggedUser.getId())
                .username(loggedUser.getUsername())
                .firstName(loggedUser.getFirstName())
                .lastName(loggedUser.getLastName())
                .role(loggedUser.getRole().name())
                .email(loggedUser.getUsername())
                .build();
    }
}
