package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.request.PostCreate;
import com.jackcomunity.emotionCommunity.request.PostEdit;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import com.jackcomunity.emotionCommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public String list(Model model) {
        List<PostResponse> posts = postService.getList();
        model.addAttribute("posts", posts);
        return "post/posts";
    }

    @GetMapping("/write")
    public String addForm(){
        return "post/postForm";
    }

    @PostMapping("/write")
    public String create(PostCreate postCreate, Authentication authentication){
        String username = authentication.getName();
        postService.write(postCreate, username);
        return "redirect:/board/posts";
    }

    @GetMapping("/posts/{postId}")
    public String get(@PathVariable Long postId, Model model){
        PostResponse postResponse = postService.get(postId);
        model.addAttribute("post", postResponse);
        return "post/postView";
    }

    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable Long postId, Model model){
        PostResponse postResponse = postService.get(postId);
        model.addAttribute("editPost", postResponse);
        return "/post/postEdit";
    }

    @PostMapping("/edit/{postId}")
    public String editSave(@PathVariable Long postId, PostEdit postEdit){
        postService.edit(postId, postEdit);
        return "redirect:/board/posts";
    }

    @GetMapping("/delete/{postId}")
    public String delete(@PathVariable Long postId){
        postService.delete(postId);
        return "redirect:/board/posts";
    }
}
