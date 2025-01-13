package com.globallogic.test.user.persistence;

import com.globallogic.test.user.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser() {
        User user = saveUser();
        assertThat(userRepository.existsById(user.getId())).isTrue();
        assertThat(user.getId()).isNotNull();
    }

    @Test
    void testLoadUserByUserName() {
        saveUser();
        Optional<User> user = userRepository.findUserByEmail(TestUtil.EMAIL);
        Assertions.assertThat(user).isNotNull();
    }

    private User saveUser() {
        return  userRepository.save(User.builder()
                .active(true)
                .email(TestUtil.EMAIL)
                .name("test")
                .lastLogin(LocalDateTime.now())
                .createdDate(LocalDate.now())
                .password("12345")
                .build());
    }


}
