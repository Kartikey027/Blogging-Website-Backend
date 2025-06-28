package com.kartikey.blog.service.comment;

import com.kartikey.blog.dto.comment.CommentRequest;
import com.kartikey.blog.dto.comment.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse addComment(Long postId, CommentRequest request, String username);
    List<CommentResponse> getCommentsByPostId(Long postId);
    void deleteComment(Long commentId,String username);
}
