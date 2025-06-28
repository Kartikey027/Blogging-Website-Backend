package com.kartikey.blog.controller;

import com.kartikey.blog.dto.like.LikeResponse;
import com.kartikey.blog.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> toggleLike(@PathVariable Long postId, Authentication authentication){
        String result= likeService.toggleLike(postId, authentication.getName());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{postId}/likes")
    public ResponseEntity<Long> getLikesCount(@PathVariable Long postId){
        return ResponseEntity.ok(likeService.getLikeCount(postId));
    }

    @GetMapping("/{postId}/likes/details")
    public ResponseEntity<List<LikeResponse>> getLikeDetails(@PathVariable Long postId){
        return ResponseEntity.ok(likeService.getLikesForPosts(postId));
    }
}
