package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.response.PostResponse;
import com.jackcomunity.emotionCommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public String list(Model model) {
        List<PostResponse> posts = postService.getList();
        model.addAttribute("posts", posts);
        return "index";
    }
}
