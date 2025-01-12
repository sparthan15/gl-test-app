package com.globallogic.test.user.controller.user;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private Set<Phone> phones;
}
