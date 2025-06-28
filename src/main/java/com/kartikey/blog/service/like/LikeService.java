package com.kartikey.blog.service.like;

import com.kartikey.blog.dto.like.LikeResponse;

import java.util.List;

public interface LikeService {
    String toggleLike(Long postId,String username);
    Long getLikeCount(Long postId);
    List<LikeResponse> getLikesForPosts(Long postId);
}
