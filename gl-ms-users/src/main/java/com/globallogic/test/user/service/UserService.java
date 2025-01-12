package com.globallogic.test.user.service;

import com.globallogic.test.user.controller.user.UserRequest;
import com.globallogic.test.user.controller.user.UserResponse;
import com.globallogic.test.user.persistence.User;
import com.globallogic.test.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface UserService {
    UserResponse addUser(UserRequest userRequest);
}

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = userRepository.save(User.builder()
                .name(userRequest.getName())
                .active(true)
                .email(userRequest.getEmail())
                .build());
        return UserResponse.builder()
                .email(user.getEmail())
                .build();
    }
}
