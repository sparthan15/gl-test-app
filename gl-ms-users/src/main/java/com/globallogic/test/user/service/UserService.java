package com.globallogic.test.user.service;

import com.globallogic.test.user.controller.user.UserRequest;
import com.globallogic.test.user.controller.user.UserResponse;
import com.globallogic.test.user.persistence.User;
import com.globallogic.test.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

public interface UserService {
    UserResponse addUser(UserRequest userRequest);
}

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final PhoneMapper phoneMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = userRepository.save(User.builder()
                .name(userRequest.getName())
                .createdDate(LocalDate.now())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .active(true)
                .phones(userRequest.getPhones().stream()
                        .map(phoneMapper::requestToEntity)
                        .collect(Collectors.toSet()))
                .email(userRequest.getEmail())
                .build());
        return UserResponse.builder()
                .id(user.getId().toString())
                .name(user.getName())
                .phones(user.getPhones())
                .email(user.getEmail())
                .build();
    }

}
