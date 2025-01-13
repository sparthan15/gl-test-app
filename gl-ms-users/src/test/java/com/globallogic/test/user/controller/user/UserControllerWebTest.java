package com.globallogic.test.user.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.test.user.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerWebTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void given_bodyIsCorrect_then_returnData() throws Exception {
        String payload = TestUtil.userRequestPayload;
        MvcResult result = this.mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        UserResponse userResponse =
                objectMapper.readValue(result.getResponse().getContentAsByteArray(),
                        UserResponse.class);
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getUser()).isNotNull();
        assertThat(userResponse.getToken()).isNotNull();
    }

    @Test
    void given_bodyIsEmpty_then_badRequest() throws Exception {
        String payload = "";
        this.mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void given_userAlreadyExists_then_conflict() throws Exception {
        String payload = TestUtil.userRequestPayload;
        this.mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
