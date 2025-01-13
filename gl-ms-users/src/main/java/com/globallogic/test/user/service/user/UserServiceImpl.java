package com.globallogic.test.user.service.user;

import com.globallogic.test.user.controller.auth.AuthRequest;
import com.globallogic.test.user.controller.auth.AuthResponse;
import com.globallogic.test.user.controller.user.UserRequest;
import com.globallogic.test.user.controller.user.UserResponse;
import com.globallogic.test.user.persistence.User;
import com.globallogic.test.user.persistence.UserRepository;
import com.globallogic.test.user.service.login.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final PhoneMapper phoneMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        userRepository.findUserByEmail(userRequest.getEmail())
                .ifPresent(u -> {
                    throw new UserAlreadyExistException();
                });
        User user = userRepository.save(User.builder()
                .name(userRequest.getName())
                .createdDate(LocalDate.now())
                .lastLogin(LocalDateTime.now())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .active(true)
                .phones(userRequest.getPhones().stream()
                        .map(phoneMapper::requestToEntity)
                        .collect(Collectors.toSet()))
                .email(userRequest.getEmail())
                .build());
        AuthResponse authResponse = authenticationService.login(AuthRequest.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword()).build());
        return UserResponse.builder()
                .token(authResponse.getToken())
                .lastLogin(authResponse.getLastLogin())
                .user(UserResponse.UserData.builder().id(user.getId().toString())
                        .name(user.getName())
                        .phones(user.getPhones())
                        .email(user.getEmail())
                        .build()).build();
    }

}