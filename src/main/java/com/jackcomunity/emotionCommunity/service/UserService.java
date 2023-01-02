package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.entity.Roles;
import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.UserCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(UserCreate userCreate){
        String encodedPassword = passwordEncoder.encode(userCreate.getPassword());
        User registerUser = User.builder()
                .username(userCreate.getUsername())
                .password(encodedPassword)
                .role(Roles.USER)
                .build();

        userRepository.save(registerUser);
    }
}
