package com.globallogic.test.user.service.user;

import com.globallogic.test.user.controller.user.UserRequest;
import com.globallogic.test.user.controller.user.UserResponse;

public interface UserService {
    UserResponse addUser(UserRequest userRequest);
}

