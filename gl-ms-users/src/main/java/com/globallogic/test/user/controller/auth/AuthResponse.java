package com.globallogic.test.user.controller.auth;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class AuthResponse {
    private String email;
    private String id;
    private LocalDate createdAt;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;

}
