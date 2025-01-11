package com.globallogic.test.user.service;

import com.globallogic.test.user.controller.LoginRequest;
import com.globallogic.test.user.controller.LoginResponse;
import com.globallogic.test.user.security.JwtUtil;
import com.globallogic.test.user.persistence.User;
import com.globallogic.test.user.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    public static final String EMAIL = "test@gmail.com";
    public static final String TOKEN = "xxxxxxx";
    @InjectMocks
    private AuthenticationServiceImpl authenticationServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtil jwtUtil;

    @Test
    void testLogin() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .email(EMAIL)
                .active(true)
                .createdAt(LocalDate.now())
                .lastLogin(LocalDate.now())
                .firstName("test")
                .lastName("test2")
                .build();
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(EMAIL, "test"));
        when(userRepository.findUserByEmail(EMAIL)).thenReturn(user);
        when(jwtUtil.createToken(user)).thenReturn(TOKEN);
        LoginResponse response = authenticationServiceImpl.login(LoginRequest.create(EMAIL,
                "21234"));
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(user.getId().toString());
        assertThat(response.getToken()).isEqualTo(TOKEN);
    }


}
