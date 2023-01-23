package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.exception.EmotionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnCheckedExceptionController {
    @ExceptionHandler(EmotionException.class)
    public String exception(EmotionException exception, Model model){
        model.addAttribute("code", exception.getStatusCode());
        model.addAttribute("message", exception.getMessage());
        return "error/exception";
    }
}
