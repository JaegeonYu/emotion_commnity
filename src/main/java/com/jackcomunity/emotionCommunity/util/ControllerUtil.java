package com.jackcomunity.emotionCommunity.util;

import com.jackcomunity.emotionCommunity.security.CustomUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
@Controller
public class ControllerUtil {
    public static void existsSession(Model model, CustomUserDetails user){
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("emotion", user.getEmotion());
    }

}
