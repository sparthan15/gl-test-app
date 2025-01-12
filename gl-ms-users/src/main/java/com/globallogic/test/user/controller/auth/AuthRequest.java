package com.globallogic.test.user.controller.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequest {
    private String email;
    private String password;

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static AuthRequest create(String email, String password) {
        return new AuthRequest(email, password);
    }

}