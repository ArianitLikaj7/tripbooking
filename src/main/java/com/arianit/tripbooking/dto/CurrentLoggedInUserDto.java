package com.arianit.tripbooking.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentLoggedInUserDto {
        private Long userId;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String role;

}
