package com.globallogic.test.user;

import com.globallogic.test.user.controller.user.PhoneRequest;
import com.globallogic.test.user.controller.user.UserRequest;
import com.globallogic.test.user.persistence.Phone;
import com.globallogic.test.user.persistence.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class TestUtil {
    public static final String EMAIL = "test@gmail.com";
    public static final String TOKEN = "xxxxxxx";
    public static final LocalDateTime LAST_LOGIN = LocalDateTime.now();
    public static final LocalDate CREATED_AT = LocalDate.now();
    public static final UUID RANDOM_UUID = UUID.randomUUID();
    public static final UserRequest userRequest = UserRequest.builder()
            .email("test@gmail.com")
            .password("123456")
            .phones(Set.of(PhoneRequest.builder().number(1234L)
                    .cityCode(123)
                    .countryCode("1234")
                    .build()))
            .build();

    public static User userEntity = User.builder()
            .id(RANDOM_UUID)
            .email(EMAIL)
            .active(true)
            .createdDate(CREATED_AT)
            .lastLogin(LAST_LOGIN)
            .password("12345")
            .name("test")
            .build();

    public static Phone phoneEntity = Phone.builder()
            .number(1L)
            .countryCode("1234")
            .id(1L)
            .cityCode(1)
            .build();
    public static PhoneRequest phoneRequest = PhoneRequest.builder()
            .countryCode("1234")
            .cityCode(1)
            .number(1L)
            .build();
}
