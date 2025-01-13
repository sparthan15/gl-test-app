package com.globallogic.test.user.config.security;

import com.globallogic.test.user.TestUtil;
import com.globallogic.test.user.persistence.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;
    @Mock
    private UserRepository userRepository;

    @Test
    void testLoadUserByUserName(){
        Mockito.when(userRepository.findUserByEmail(TestUtil.EMAIL)).thenReturn(Optional.of(TestUtil.userEntity));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(TestUtil.EMAIL);
        Assertions.assertThat(userDetails).isNotNull();
        Assertions.assertThat(userDetails.getUsername()).isEqualTo(TestUtil.userEntity.getEmail());
        Mockito.verify(userRepository).findUserByEmail(TestUtil.EMAIL);
    }
}
