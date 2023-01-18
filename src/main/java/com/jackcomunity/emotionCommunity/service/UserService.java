package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.request.EmotionEdit;
import com.jackcomunity.emotionCommunity.request.UserEdit;
import com.jackcomunity.emotionCommunity.security.CustomUserDetails;
import com.jackcomunity.emotionCommunity.util.Emotion;
import com.jackcomunity.emotionCommunity.util.Roles;
import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.UserCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(UserCreate userCreate) {
        String encodedPassword = passwordEncoder.encode(userCreate.getPassword());
        userCreate.setPassword(encodedPassword);
        userCreate.setRole(Roles.USER);
        userCreate.setEmotion(Emotion.NEUTRAL);

        userRepository.save(userCreate.toEntity());
    }

    @Transactional
    public void edit(UserEdit userEdit) {
        User user = userRepository.findByUsername(userEdit.getUsername()).orElseThrow();


        user.edit(userEdit.getNickname(), userEdit.getEmotion(), passwordEncoder.encode(userEdit.getPassword()));
    }

    @Transactional
    public void emotionEdit(EmotionEdit emotionEdit){
        System.out.println(emotionEdit.getUsername());
        User user = userRepository.findByUsername(emotionEdit.getUsername()).orElseThrow();
        user.edit(emotionEdit.getEmotion());
    }
}
