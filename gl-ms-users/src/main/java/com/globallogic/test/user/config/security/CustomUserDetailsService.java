package com.globallogic.test.user.config.security;

import com.globallogic.test.user.persistence.User;
import com.globallogic.test.user.persistence.UserRepository;
import com.globallogic.test.user.service.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userByEmail = userRepository.findUserByEmail(email);

        List<String> roles = new ArrayList<>();
        roles.add("USER");
        if(userByEmail.isPresent()) {
            User user = userByEmail.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(roles.toArray(new String[0]))
                    .build();
        }else {
            throw new UserNotFoundException();
        }
    }

}
