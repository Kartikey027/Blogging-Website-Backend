package com.kartikey.blog.controller;

import com.kartikey.blog.dto.post.PostRequest;
import com.kartikey.blog.dto.post.PostResponse;
import com.kartikey.blog.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest request, Authentication authentication) {
        try {
            String username = authentication.getName();
            System.out.println("Authenticated as: " + username);
            PostResponse response = postService.createPost(request, username);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Log stacktrace to console
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden: " + e.getMessage());
        }
    }




    @GetMapping("/published")
    public ResponseEntity<List<PostResponse>> getAllPublishedPost(){
        return ResponseEntity.ok(postService.getAllPublishedPost());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,Authentication authentication){
        postService.deletePost(id,authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id,@RequestBody PostRequest request, Authentication authentication){
        PostResponse updated=postService.updatePost(id,request, authentication.getName());
        return ResponseEntity.ok(updated);
    }
}
