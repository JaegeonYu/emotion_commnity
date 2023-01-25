package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.exception.EmotionException;
import com.jackcomunity.emotionCommunity.request.MessageCreate;
import com.jackcomunity.emotionCommunity.security.CustomUserDetails;
import com.jackcomunity.emotionCommunity.util.ControllerUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;

@ControllerAdvice
public class UnCheckedExceptionController {
    @ExceptionHandler(EmotionException.class)
    public String exception(EmotionException exception, Model model){
        model.addAttribute("code", exception.getStatusCode());
        model.addAttribute("message", exception.getMessage());
        return "error/exception";
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String redirect(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){
        MessageCreate message = new MessageCreate("비밀번호가 다릅니다.", "/account/emotion", RequestMethod.GET, null);
        System.out.println(userDetails.getNickname());
        System.out.println(userDetails.getEmotion());
        ControllerUtil.existsSession(model, userDetails);
        model.addAttribute("params", message);
        return "fragments/messageRedirect";
    }
}
