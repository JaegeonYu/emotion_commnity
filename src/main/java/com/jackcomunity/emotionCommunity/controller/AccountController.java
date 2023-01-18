package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.request.EmotionEdit;
import com.jackcomunity.emotionCommunity.request.UserEdit;
import com.jackcomunity.emotionCommunity.request.UserCreate;
import com.jackcomunity.emotionCommunity.security.CustomUserDetails;
import com.jackcomunity.emotionCommunity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String signupForm() {
        return "account/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreate user) {
        userService.save(user);
        return "redirect:/account/login";
    }

    @GetMapping("/edit")
    public String emotionForm(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        model.addAttribute("nickname", customUserDetails.getNickname());
        model.addAttribute("email", customUserDetails.getEmail());
        return "account/userEdit";
    }

//    @PutMapping("/modify")
//    @ResponseBody
//    public ResponseEntity<String> emotionEdit(@RequestBody UserEdit userEdit) {
//        System.out.println(userEdit);
//
////
//        userService.modify(userEdit);
//        System.out.println("next modify ==========================");
//        System.out.println(userEdit.getPassword());
//        Authentication authenticate = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userEdit.getUsername(), userEdit.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//        System.out.println("next context ===================");
//        return new ResponseEntity("success" , HttpStatus.OK);
//    }
    @PostMapping("/edit")
    public String userEdit(UserEdit userEdit) {
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
    public String emotionFrom(){
        return "account/emotionEdit";
    }

    @PostMapping("/emotion")
    public String emotionEdit(EmotionEdit emotionEdit, @AuthenticationPrincipal CustomUserDetails userDetails){
        System.out.println(emotionEdit);
        userService.emotionEdit(emotionEdit);
        updateAuthToken(userDetails.getUsername(), emotionEdit.getPassword());
        return "redirect:/posts";
    }
}
