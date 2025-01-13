package com.globallogic.test.user.service.user;

import com.globallogic.test.user.TestUtil;
import com.globallogic.test.user.controller.auth.AuthRequest;
import com.globallogic.test.user.controller.auth.AuthResponse;
import com.globallogic.test.user.controller.user.PhoneRequest;
import com.globallogic.test.user.controller.user.UserRequest;
import com.globallogic.test.user.controller.user.UserResponse;
import com.globallogic.test.user.persistence.User;
import com.globallogic.test.user.persistence.UserRepository;
import com.globallogic.test.user.service.login.AuthenticationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    @Mock
    private AuthenticationService authenticationService;

    private UserRequest userRequest = TestUtil.userRequest;

    @Test
    void testAddUser() {
        User user = TestUtil.userEntity;
        PhoneRequest phoneRequest = userRequest.getPhones().stream().findAny().get();
        when(phoneMapper.requestToEntity(phoneRequest)).thenReturn(TestUtil.phoneEntity);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        AuthResponse authResponse = AuthResponse.builder()
                .id(UUID.randomUUID().toString())
                .token(TestUtil.TOKEN)
                .lastLogin(user.getLastLogin())
                .email(userRequest.getEmail())
                .build();
        when(authenticationService.login(any(AuthRequest.class))).thenReturn(authResponse);
        UserResponse userResponse = userService.addUser(userRequest);

        verify(userRepository).save(any(User.class));
        verify(userRepository).findUserByEmail(user.getEmail());
        verify(phoneMapper).requestToEntity(phoneRequest);
        verify(authenticationService).login(any(AuthRequest.class));
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getLastLogin()).isNotNull();
        assertThat(userResponse.getToken()).isEqualTo(authResponse.getToken());
        assertThat(userResponse.getLastLogin()).isEqualTo(authResponse.getLastLogin());
        assertThat(userResponse.getUser().getId()).isNotNull();
        assertThat(userResponse.getUser().getPhones()).isNotEmpty();
    }

    @Test
    void given_aUserAlreadyExists_then_throwException() {
        User user = TestUtil.userEntity;
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        Assertions.assertThatThrownBy(() -> userService.addUser(userRequest))
                .isInstanceOf(UserAlreadyExistException.class);

        verify(userRepository, never()).save(any(User.class));
        verify(authenticationService, never()).login(any());
        verify(userRepository).findUserByEmail(user.getEmail());
    }
}
