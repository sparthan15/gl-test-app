package com.globallogic.test.user.controller.user;

import com.globallogic.test.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    private final UserRequest userRequest = UserRequest.builder()
            .email("test@gmail.com")
            .password("123456")
            .phones(Set.of(Phone.builder().number(1234L)
                    .cityCode(123)
                    .countryCode("1234")
                    .build()))
            .build();

    @Test
    void testAddUser() {
        Mockito.when(userService.addUser(userRequest)).thenReturn(UserResponse.builder().build());
        UserResponse response = userController.addUser(userRequest);
        Assertions.assertThat(response).isNotNull();
        Mockito.verify(userService).addUser(userRequest);
    }
}
