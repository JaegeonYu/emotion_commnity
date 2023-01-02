package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.repository.UserRepository;
import com.jackcomunity.emotionCommunity.request.UserCreate;
import com.jackcomunity.emotionCommunity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequiredArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountController {
    private final UserService userService;
    @GetMapping("/login")
    public String login(){
        return "account/login";
    }
    @GetMapping("/signup")
    public String signupForm(){
        return "account/signup";
    }
    @PostMapping("/signup")
    public String signup(UserCreate user){
        userService.save(user);
        return "redirect:/account/login";
    }
}
