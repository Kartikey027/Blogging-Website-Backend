package com.kartikey.blog.controller;

import com.kartikey.blog.dto.comment.CommentRequest;
import com.kartikey.blog.dto.comment.CommentResponse;
import com.kartikey.blog.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    public final CommentService commentService;

    @PostMapping("/post/{id}")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long id, @RequestBody CommentRequest request, Authentication authentication){
        String username= authentication.getName();
        return ResponseEntity.ok(commentService.addComment(id,request,username));
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<List<CommentResponse>> getCommentByPost(@PathVariable Long id){
        return ResponseEntity.ok(commentService.getCommentsByPostId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id,Authentication authentication){
        commentService.deleteComment(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
