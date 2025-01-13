package com.globallogic.test.user.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id" +
            ".UUIDGenerator")
    private UUID id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private LocalDateTime lastLogin;
    @CreatedDate
    private LocalDate createdDate;
    private Boolean active;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_fk")
    private Set<Phone> phones;
}
