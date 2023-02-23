package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.repository.PostRepository;
import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.UserCreate;
import com.jackcomunity.emotionCommunity.util.Emotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void save_test(){
        UserCreate user = UserCreate.builder().username("jack").password(passwordEncoder.encode("1234"))
                .email("yjk98053@gmail.com").nickname("biobebe").build();

        userService.save(user);
        Optional<User> findUser = userRepository.findByUsername("jack");
        Assertions.assertEquals(findUser.get().getUsername(), user.getUsername());
    }
}
