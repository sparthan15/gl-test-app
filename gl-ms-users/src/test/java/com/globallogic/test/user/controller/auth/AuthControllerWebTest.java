package com.globallogic.test.user.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.test.user.TestUtil;
import com.globallogic.test.user.controller.user.UserResponse;
import com.globallogic.test.user.persistence.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.globallogic.test.user.TestUtil.EMAIL;
import static com.globallogic.test.user.TestUtil.VALID_PASSWORD;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerWebTest {

    public static final String AUTHORIZATION = "Authorization";
    @Autowired
    private AuthController authController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    private UserResponse userResponse;
    @Test
    void contextLoads() {
        assertThat(authController).isNotNull();
    }

    @BeforeEach
    public void setUp() throws Exception {
        if(userResponse == null){
            userResponse = getUserData();
        }
    }

    @Test
    void given_noPayloadSent_then_badRequest() throws Exception {
        this.mockMvc.perform(post("/auth/login")
                        .header(AUTHORIZATION, format("Bearer %s", userResponse.getToken())))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void given_payloadSent_then_andTokenSent_returnData() throws Exception {
        String payload = "{\n" +
                "    \"email\": \"" + EMAIL + "\",\n" +
                "    \"password\": \"" + VALID_PASSWORD + "\"\n" +
                "}";
        MvcResult result = this.mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(payload)
                        .header(AUTHORIZATION, format("Bearer %s", userResponse.getToken())))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        Assertions.assertThat(result.getResponse().toString()).isNotNull();
    }

    private UserResponse getUserData() throws Exception {
        String payload = TestUtil.userRequestPayload;
        MvcResult result = this.mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        return objectMapper.readValue(result.getResponse().getContentAsByteArray(),
                UserResponse.class);
    }

    @Test
    void given_userDoesNotExists_then_unauthorized() throws Exception {
        String payload = "{\n" +
                "    \"email\": \"" + EMAIL + "\",\n" +
                "    \"password\": \"" + VALID_PASSWORD + "\"\n" +
                "}";
        MvcResult result = this.mockMvc.perform(post("/auth/login")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isUnauthorized()).andReturn();
        Assertions.assertThat(result.getResponse().toString()).isNotNull();
    }

}
