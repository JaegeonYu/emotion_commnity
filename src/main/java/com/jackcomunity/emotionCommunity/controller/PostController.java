package com.jackcomunity.emotionCommunity.controller;

import com.jackcomunity.emotionCommunity.request.PostCreate;
import com.jackcomunity.emotionCommunity.request.PostEdit;
import com.jackcomunity.emotionCommunity.response.PageDto;
import com.jackcomunity.emotionCommunity.response.PostResponse;
import com.jackcomunity.emotionCommunity.security.CustomUserDetails;
import com.jackcomunity.emotionCommunity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public String list(Model model, @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Page<PostResponse> posts = postService.getList(pageable);
        PageDto<PostResponse> postPageResponse = PageDto.of(posts);
        model.addAttribute("posts", postPageResponse);
        if(userDetails != null){
            existsSession(model, userDetails);
        }

        return "post/posts";
    }

    @GetMapping("/posts/read/{postId}")
    public String get(@PathVariable Long postId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if(userDetails != null){
            PostResponse postResponse = postService.getWithEmotion(postId, userDetails.getEmotion());
            if(postResponse.getCommentResponses()!= null){
                model.addAttribute("comments", postResponse.getCommentResponses());
            }
            existsSession(model, userDetails);
            model.addAttribute("post",postResponse);
        }
        if(userDetails == null){
            PostResponse postResponse = postService.get(postId);
            if(postResponse.getCommentResponses()!=null){
                model.addAttribute("comments", postResponse.getCommentResponses());
            }
            model.addAttribute("post",postResponse);
        }
        return "post/postView";
    }

    @GetMapping("/posts/search")
    public String search(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         String searchText, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Page<PostResponse> searchPosts = postService.search(searchText, pageable);
        PageDto searchPageResponse = PageDto.of(searchPosts);
        model.addAttribute("posts", searchPageResponse);
        model.addAttribute("searchText", searchText);
        if(userDetails != null){
            existsSession(model, userDetails);
        }
        return "post/postSearch";
    }


    @GetMapping("/posts/write")
    public String addForm(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if(userDetails != null){
            existsSession(model, userDetails);
        }
        model.addAttribute("postCreate", new PostCreate());
        return "post/postForm";
    }

    @PostMapping("/posts")
    public String create(@Valid PostCreate postCreate, BindingResult result, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        if(result.hasErrors()){
            existsSession(model, userDetails);
            return "post/postForm";
        }
        String username = userDetails.getUsername();
        postService.write(postCreate, username);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{postId}")
    public String edit(@PathVariable Long postId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        PostResponse post = postService.get(postId);
        if(userDetails != null){
            existsSession(model, userDetails);
        }
        if(!userDetails.getUsername().equals(post.getUsername())) return "/account/login"; // 작성자가 아니라는 페이지
        model.addAttribute("postEdit", new PostEdit(post.getTitle(), post.getContent()));
        model.addAttribute("postId", postId);
        return "post/postEdit";
    }

    @PutMapping("/posts/{postId}")
    public String editSave(@PathVariable Long postId, @Valid PostEdit postEdit, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("postEdit", postEdit);
            return "post/postEdit";
        }
        postService.edit(postId, postEdit);
        return "redirect:/posts";
    }

    @DeleteMapping("/posts/{postId}")
    public String delete(@PathVariable Long postId) {
        postService.delete(postId);
        return "redirect:/posts";
    }
    private void existsSession(Model model, CustomUserDetails user){
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("emotion", user.getEmotion());
    }
}
