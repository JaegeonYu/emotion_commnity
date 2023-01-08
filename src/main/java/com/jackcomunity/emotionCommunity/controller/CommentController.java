package com.jackcomunity.emotionCommunity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jackcomunity.emotionCommunity.request.CommentCreate;
import com.jackcomunity.emotionCommunity.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/board/posts/{postId}/write")
    public String save(@PathVariable Long postId, CommentCreate commentCreate, Authentication authentication) throws JsonProcessingException {

        commentService.save(commentCreate, authentication.getName(), postId);
        return "redirect:/board/posts/{postId}";
    }
}
