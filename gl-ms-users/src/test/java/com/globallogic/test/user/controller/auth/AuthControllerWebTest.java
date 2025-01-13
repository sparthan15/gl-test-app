package com.globallogic.test.user.controller.auth;

import com.globallogic.test.user.config.security.JwtUtil;
import com.globallogic.test.user.service.login.AuthenticationService;
import com.globallogic.test.user.service.login.LoginErrorException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
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
    void given_noPayloadSent_then_badRequest() throws Exception {
        this.mockMvc.perform(post("/auth/login"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void given_payloadSent_then_loginSucess() throws Exception {
        String payload = "{\n" +
                "    \"email\": \"carlosgamboa15@gmail.com\",\n" +
                "    \"password\": \"1A8asasddaa\"\n" +
                "}";
        MvcResult result = this.mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        Assertions.assertThat(result.getResponse().toString()).isNotNull();
    }

    @Test
    void given_userDoesNotExists_then_unauthorized() throws Exception {
        String payload = "{\n" +
                "    \"email\": \"carlosgamboa15@gmail.com\",\n" +
                "    \"password\": \"1A8asasddaa\"\n" +
                "}";
        Mockito.doThrow(LoginErrorException.class).when(authenticationService).login(any());
        MvcResult result = this.mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isUnauthorized()).andReturn();
        Assertions.assertThat(result.getResponse().toString()).isNotNull();
    }

}
