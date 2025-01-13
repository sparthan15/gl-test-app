package com.globallogic.test.user.controller.user;

import com.globallogic.test.user.persistence.Phone;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class UserResponse {
    private String token;
    private LocalDateTime lastLogin;
    private boolean isActive;
    private UserData user;

    @Builder
    @Data
    public static class UserData{
        private String id;
        private String name;
        private String email;
        private Set<Phone> phones;
    }
}
