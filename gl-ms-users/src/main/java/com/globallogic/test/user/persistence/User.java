package com.globallogic.test.user.persistence;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class User {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate lastLogin;
    private LocalDate createdAt;
    private Boolean active;
}
