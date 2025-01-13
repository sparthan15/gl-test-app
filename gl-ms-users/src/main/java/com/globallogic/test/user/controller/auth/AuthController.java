package com.globallogic.test.user.controller.auth;

import com.globallogic.test.user.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthResponse login( @RequestBody AuthRequest authRequest) {
        return authenticationService.login(authRequest);
    }
}
