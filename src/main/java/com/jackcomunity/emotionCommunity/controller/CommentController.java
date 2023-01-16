package com.jackcomunity.emotionCommunity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jackcomunity.emotionCommunity.request.CommentAjaxCreate;
import com.jackcomunity.emotionCommunity.request.CommentEdit;
import com.jackcomunity.emotionCommunity.security.CustomUserDetails;
import com.jackcomunity.emotionCommunity.request.CommentCreate;
import com.jackcomunity.emotionCommunity.response.CommentResponse;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import com.jackcomunity.emotionCommunity.service.CommentService;
import com.jackcomunity.emotionCommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/posts/{postId}/comments")
    public String save(@PathVariable Long postId, CommentCreate commentCreate, Authentication authentication){

        commentService.save(commentCreate, authentication.getName(), postId);
        return "redirect:/posts/read/{postId}";
    }

//    @PostMapping("/posts/{postId}/comments")
//    public String saveAjax(@PathVariable Long postId, @RequestBody CommentAjaxCreate commentCreate, Model model){
//            commentService.ajaxCreate(commentCreate, postId);
//            model.addAttribute("comments", commentService.getList(postId));
//        return "post/postView :: #commentTable";
//    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public String editForm(@PathVariable Long postId, @PathVariable Long commentId, Model model,
                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        PostResponse postResponse = postService.get(postId);
        List<CommentResponse> comments = postResponse.getCommentResponses();
        if (!comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }
        if (userDetails != null) {
            model.addAttribute("nickname", userDetails.getNickname());
        }
        model.addAttribute("post", postResponse);
        model.addAttribute("editCommentId", commentId);
        return "post/postCommentEdit";
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public String edit(CommentEdit commentEdit, @PathVariable Long commentId, @PathVariable Long postId){
        commentService.edit(commentEdit, commentId);
        return "redirect:/posts/read/{postId}";
    }


    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public String delete(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.delete(commentId);
        return "redirect:/posts/read/{postId}";
    }
}
