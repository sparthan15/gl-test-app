package com.globallogic.test.user.service;

import com.globallogic.test.user.TestUtil;
import com.globallogic.test.user.controller.auth.AuthRequest;
import com.globallogic.test.user.controller.auth.AuthResponse;
import com.globallogic.test.user.persistence.User;
import com.globallogic.test.user.persistence.UserRepository;
import com.globallogic.test.user.config.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static com.globallogic.test.user.TestUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {


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
        User user = TestUtil.userEntity;
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(EMAIL, "test"));
        when(userRepository.findUserByEmail(EMAIL)).thenReturn(user);
        when(jwtUtil.createToken(user)).thenReturn(TOKEN);
        AuthResponse response = authenticationServiceImpl.login(AuthRequest.create(EMAIL,
                "21234"));
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(user.getId().toString());
        assertThat(response.getToken()).isEqualTo(TOKEN);
        assertThat(response.getLastLogin()).isEqualTo(LAST_LOGIN);
        assertThat(response.getCreatedAt()).isEqualTo(CREATED_AT);
        assertThat(response.getId()).isEqualTo(RANDOM_UUID.toString());
    }


}
