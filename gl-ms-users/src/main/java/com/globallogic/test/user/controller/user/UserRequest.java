package com.globallogic.test.user.controller.user;

import com.globallogic.test.user.config.password.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    @Email
    private String email;
    @Password
    private String password;
    private Set<PhoneRequest> phones;
}
