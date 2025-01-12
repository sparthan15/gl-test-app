package com.globallogic.test.user.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    default User findUserByEmail(String email) {
        //whatever the email is ok, but the password must be 123456..
        return User.builder()
                .id(UUID.randomUUID())
                .email(email)
                .createdAt(LocalDate.now())
                .lastLogin(LocalDate.now())
                .name("test")
                .active(true)
                .password("{bcrypt}$2b$04$fZkWpBDU.vP.5IyONhwMyu1Xlxikr6NjLQxj1J6haw3YX1H8RrHcO")
                .build();
    }
}
