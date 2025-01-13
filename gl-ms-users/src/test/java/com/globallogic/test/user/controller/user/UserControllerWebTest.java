package com.globallogic.test.user.controller.user;

import com.globallogic.test.user.TestUtil;
import com.globallogic.test.user.service.user.UserAlreadyExistException;
import com.globallogic.test.user.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerWebTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testAddUserEndpoint() throws Exception {
        String payload = TestUtil.userRequestPayload;
        this.mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void testAddUserEndpoint_when_bodyIsEmpty_then_badRequest() throws Exception {
        String payload = "";
        this.mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void given_userAlreadyExists_then_conflict() throws Exception {
        Mockito.doThrow(UserAlreadyExistException.class).when(userService).addUser(Mockito.any());
        String payload = TestUtil.userRequestPayload;
        this.mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(payload))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
