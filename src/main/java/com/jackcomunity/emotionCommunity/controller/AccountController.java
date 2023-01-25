package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.request.*;
import com.jackcomunity.emotionCommunity.security.CustomUserDetails;
import com.jackcomunity.emotionCommunity.service.UserService;
import com.jackcomunity.emotionCommunity.util.ControllerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.jackcomunity.emotionCommunity.util.ControllerUtil.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/account")
public class AccountController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("userCreate", new UserCreate());
        return "account/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreate user, BindingResult result, Model model) {
        userService.checkDuplicateSignup(new UserCheck(user), result);

        if(result.hasErrors()){
            model.addAttribute("userCreate", user);
            return "account/signup";
        }

        userService.save(user);
        return "redirect:/account/login";
    }

    @GetMapping("/edit")
    public String emotionForm(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        existsSession(model, userDetails);
        model.addAttribute("userEdit", UserEdit.builder().username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .nickname(userDetails.getNickname()).build());
        return "account/userEdit";
    }

    @PostMapping("/edit")
    public String userEdit(@Valid UserEdit userEdit, BindingResult result, Model model,
                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.checkDuplicateEdit(new UserCheck(userEdit), result);

        if(result.hasErrors()){
            model.addAttribute("userEdit", userEdit);
            existsSession(model, userDetails);
            return "account/userEdit";
        }
        userService.edit(userEdit);
        updateAuthToken(userEdit.getUsername(), userEdit.getPassword());

        return "redirect:/posts";
    }

    private void updateAuthToken(String username, String password) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }

    @Secured("ROLE_USER")
    @GetMapping("/emotion")
    public String emotionFrom(@AuthenticationPrincipal CustomUserDetails userDetails, Model model){
        existsSession(model, userDetails);
        return "account/emotionEdit";
    }

    @PostMapping("/emotion")
    public String emotionEdit(EmotionEdit emotionEdit, @AuthenticationPrincipal CustomUserDetails userDetails){
        userService.emotionEdit(emotionEdit);
        updateAuthToken(userDetails.getUsername(), emotionEdit.getPassword());

        return "redirect:/posts";
    }
}
