package com.globallogic.test.user.controller.user;

import com.globallogic.test.user.persistence.Phone;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private Set<Phone> phones;
}
