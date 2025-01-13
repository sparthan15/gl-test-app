package com.globallogic.test.user.service.login;

import com.globallogic.test.user.config.security.JwtUtil;
import com.globallogic.test.user.controller.auth.AuthRequest;
import com.globallogic.test.user.controller.auth.AuthResponse;
import com.globallogic.test.user.persistence.User;
import com.globallogic.test.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface AuthenticationService {
    AuthResponse login(AuthRequest authRequest);
}

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtils;

    public AuthResponse login(AuthRequest authRequest) {
        Optional<User> userByEmail = userRepository.findUserByEmail(authRequest.getEmail());
        if (userByEmail.isPresent()) {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            String email = authentication.getName();

            User user = userByEmail.get();
            String token = jwtUtils.createToken(user);
            return AuthResponse.builder()
                    .id(user.getId().toString())
                    .email(email)
                    .token(token)
                    .createdAt(user.getCreatedDate())
                    .lastLogin(user.getLastLogin())
                    .isActive(user.getActive())
                    .build();
        } else {
            throw new LoginErrorException();
        }
    }
}
