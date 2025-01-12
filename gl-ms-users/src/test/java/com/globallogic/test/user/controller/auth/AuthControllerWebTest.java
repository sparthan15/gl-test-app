package com.globallogic.test.user.controller.auth;

import com.globallogic.test.user.security.JwtUtil;
import com.globallogic.test.user.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc
class AuthControllerWebTest {

    @Autowired
    private AuthController authController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthenticationService authenticationService;
    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void contextLoads() {
        assertThat(authController).isNotNull();
    }

    @Test
    void given_noPayloadSent_then_loginFails() throws Exception {
        this.mockMvc.perform(post("/users/login"))
                .andDo(print())
                .andExpect(status().isForbidden());
        //.andExpect(content().string(containsString("Yea")));
    }
}
