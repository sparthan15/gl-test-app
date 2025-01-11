package com.globallogic.test.user.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static LoginRequest create(String email, String password) {
        return new LoginRequest(email, password);
    }

}