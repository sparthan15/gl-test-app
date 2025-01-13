package com.globallogic.test.user.controller.auth;

import com.globallogic.test.user.service.login.AuthenticationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    public static final String TOKEN = "xxxxxx";
    @InjectMocks
    private AuthController authController;
    @Mock
    private AuthenticationService authenticationService;

    private final AuthRequest authRequest = AuthRequest.builder()
            .email("test@gmail.com")
            .password("1234455")
            .build();

    @Test
    void testLogin() {
        Mockito.when(authenticationService.login(authRequest)).thenReturn(AuthResponse.builder()
                .token(TOKEN).build());
        AuthResponse response = authController.login(authRequest);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getToken()).isEqualTo(TOKEN);
    }
}
