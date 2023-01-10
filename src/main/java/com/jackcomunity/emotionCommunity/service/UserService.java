package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.util.Roles;
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
        userCreate.setPassword(encodedPassword);
        userCreate.setRole(Roles.USER);

        userRepository.save(userCreate.toEntity());
    }
}
