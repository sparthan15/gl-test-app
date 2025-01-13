package com.globallogic.test.user.service;

import com.globallogic.test.user.TestUtil;
import com.globallogic.test.user.controller.user.UserRequest;
import com.globallogic.test.user.controller.user.UserResponse;
import com.globallogic.test.user.persistence.Phone;
import com.globallogic.test.user.persistence.User;
import com.globallogic.test.user.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PhoneMapper phoneMapper;

    private UserRequest userRequest = TestUtil.userRequest;

    @Test
    void testAddUser() {
        User user = TestUtil.userEntity;
        when(phoneMapper.requestToEntity(userRequest.getPhones().stream().findAny().get())).thenReturn(TestUtil.phoneEntity);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(User.builder()
                .id(UUID.randomUUID())
                .email(TestUtil.EMAIL)
                .phones(Set.of(Phone.builder()
                        .cityCode(1)
                        .countryCode("1234")
                        .number(123L)
                        .build()
                ))
                .name(user.getName())
                .build());
        UserResponse userResponse = userService.addUser(userRequest);
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isNotNull();
        assertThat(userResponse.getPhones()).isNotEmpty();

    }
}
