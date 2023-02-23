package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.repository.PostRepository;
import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.UserCheck;
import com.jackcomunity.emotionCommunity.request.UserCreate;
import com.jackcomunity.emotionCommunity.request.UserEdit;
import com.jackcomunity.emotionCommunity.util.Emotion;
import com.jackcomunity.emotionCommunity.util.Roles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(findUser.get().getUsername(), user.getUsername());
    }

    @Test
    public void edit_test(){
        saveUserToRepository();

        UserEdit userEdit = UserEdit.builder().username("jack").nickname("change").email("yjk98053@gmail.com").build();
        userEdit.setPassword("4567");
        userService.edit(userEdit);

        Optional<User> findUser = userRepository.findByUsername("jack");
        assertEquals(findUser.get().getNickname(), userEdit.getNickname());
        assertTrue(passwordEncoder.matches(userEdit.getPassword(), findUser.get().getPassword()));
    }

    private void saveUserToRepository() {
        userRepository.save(User.builder().username("jack").password(passwordEncoder.encode("1234"))
                .role(Roles.USER)
                .email("yjk98053@gmail.com").nickname("biobebe").build());
    }
}
