package com.jackcomunity.emotionCommunity.service;

import com.jackcomunity.emotionCommunity.exception.UserNotFound;
import com.jackcomunity.emotionCommunity.request.EmotionEdit;
import com.jackcomunity.emotionCommunity.request.UserCheck;
import com.jackcomunity.emotionCommunity.request.UserEdit;
import com.jackcomunity.emotionCommunity.util.Emotion;
import com.jackcomunity.emotionCommunity.util.Roles;
import com.jackcomunity.emotionCommunity.entity.User;
import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.UserCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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
        User user = userRepository.findByUsername(userEdit.getUsername()).orElseThrow(UserNotFound::new);
        user.edit(userEdit.getNickname(), passwordEncoder.encode(userEdit.getPassword()));
    }

    @Transactional
    public void emotionEdit(EmotionEdit emotionEdit){
        User user = userRepository.findByUsername(emotionEdit.getUsername()).orElseThrow(UserNotFound::new);
        user.edit(emotionEdit.getEmotion());
    }
    private boolean checkDuplicateUsername(UserCheck user){
        return userRepository.existsByUsername(user.getUsername());
    }
    private boolean checkDuplicateEmail(UserCheck user){
        return userRepository.existsByEmail(user.getEmail());
    }
    private boolean checkDuplicateNickname(UserCheck user){
        return userRepository.existsByNickname(user.getNickname());
    }

    public void checkDuplicateSignup(UserCheck user, BindingResult result){
        if(checkDuplicateUsername(user)){
            result.addError(new FieldError(user.getObjectName(), "username", "이미 가입된 아이디입니다."));
        }
        if(checkDuplicateNickname(user)){
            result.addError(new FieldError(user.getObjectName(), "nickname", "이미 존재하는 별명입니다"));
        }
        if(checkDuplicateEmail(user)){
            result.addError(new FieldError(user.getObjectName(), "email", "이미 가입된 이메일입니다."));
        }
    }

    public void checkDuplicateEdit(UserCheck user, BindingResult result){
        if(checkDuplicateNickname(user)){
            result.addError(new FieldError(user.getObjectName(), "nickname", "이미 존재하는 별명입니다"));
        }
    }
}
