package com.globallogic.test.user.service;

import com.globallogic.test.user.controller.LoginRequest;
import com.globallogic.test.user.controller.LoginResponse;
import com.globallogic.test.user.security.JwtUtil;
import com.globallogic.test.user.persistence.User;
import com.globallogic.test.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

public interface AuthenticationService {
    LoginResponse login(LoginRequest loginRequest);
}

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtils;

    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);
        String token = jwtUtils.createToken(user);
        return LoginResponse.builder()
                .id(user.getId().toString())
                .email(email)
                .token(token)
                .createdAt(user.getCreatedAt())
                .lastLogin(user.getLastLogin())
                .isActive(user.getActive())
                .build();
    }
}
