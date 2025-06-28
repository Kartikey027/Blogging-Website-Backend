package com.kartikey.blog.service.post;

import com.kartikey.blog.dto.post.PostRequest;
import com.kartikey.blog.dto.post.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse createPost(PostRequest request, String username);
    List<PostResponse> getAllPublishedPost();
    PostResponse getPostById(Long id);
    void deletePost(Long id,String username);
    PostResponse updatePost(Long id,PostRequest request, String username);
}
