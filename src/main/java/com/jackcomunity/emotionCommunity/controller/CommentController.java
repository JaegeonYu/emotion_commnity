package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.exception.Unauthorized;
import com.jackcomunity.emotionCommunity.request.CommentEdit;
import com.jackcomunity.emotionCommunity.request.MessageCreate;
import com.jackcomunity.emotionCommunity.security.CustomUserDetails;
import com.jackcomunity.emotionCommunity.request.CommentCreate;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import com.jackcomunity.emotionCommunity.service.CommentService;
import com.jackcomunity.emotionCommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;


    @PostMapping("/posts/{postId}/comments")
    public String save(@PathVariable Long postId, @Valid CommentCreate commentCreate, BindingResult result, Model model,
                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        if(result.hasErrors()){
            MessageCreate message = new MessageCreate("댓글의 형식이 올바르지 않습니다.", "/posts/read/"+postId, RequestMethod.GET, null);
            return showMessageAndRedirect(message, model);
        }
        commentService.save(commentCreate, userDetails.getUsername(), postId);
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
        if(userDetails != null) {
            PostResponse postResponse = postService.getWithEmotion(postId, userDetails.getEmotion());
            if (postResponse.getCommentResponses() != null) {
                model.addAttribute("comments", postResponse.getCommentResponses());
            }
            model.addAttribute("post", postResponse);
        }
        if (userDetails == null) {
            PostResponse postResponse = postService.get(postId);
            if (postResponse.getCommentResponses() != null) {
                model.addAttribute("comments", postResponse.getCommentResponses());
            }
            model.addAttribute("post", postResponse);
        }
        model.addAttribute("editCommentId", commentId);
        return "post/postCommentEdit";
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public String edit(@Valid CommentEdit commentEdit, BindingResult result, @PathVariable Long commentId, @PathVariable Long postId,
                       @AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        checkUser(commentId, userDetails);
        if(result.hasErrors()){
            MessageCreate message = new MessageCreate("댓글의 형식이 올바르지 않습니다.", "/posts/read/{postID}", RequestMethod.GET, null);
            return showMessageAndRedirect(message, model);
        }
        commentService.edit(commentEdit, commentId);
        return "redirect:/posts/read/{postId}";
    }


    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public String delete(@PathVariable Long postId, @PathVariable Long commentId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        checkUser(commentId, userDetails);
        commentService.delete(commentId);
        return "redirect:/posts/read/{postId}";
    }

    private void checkUser(Long commentId, CustomUserDetails userDetails) {
        if (!userDetails.getUsername().equals(commentService.get(commentId).getUsername()))throw new Unauthorized();
    }

    private String showMessageAndRedirect(final MessageCreate params, Model model){
        model.addAttribute("params", params);
        return "fragments/messageRedirect";
    }
}
