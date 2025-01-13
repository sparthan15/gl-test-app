package com.globallogic.test.user.service.login;

import com.globallogic.test.user.controller.auth.AuthRequest;
import com.globallogic.test.user.controller.auth.AuthResponse;

public interface AuthenticationService {
    AuthResponse login(AuthRequest authRequest);
}
