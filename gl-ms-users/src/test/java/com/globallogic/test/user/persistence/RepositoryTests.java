package com.globallogic.test.user.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser() {
        User user = userRepository.save(User.builder()
                .active(true)
                .email("test@gmail.com")
                .name("test")
                .lastLogin(LocalDate.now())
                .createdAt(LocalDate.now())
                .password("12345")
                .build());
        assertThat(userRepository.existsById(user.getId())).isTrue();
        assertThat(user.getId()).isNotNull();
    }
}
