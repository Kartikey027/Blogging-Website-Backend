package com.kartikey.blog.service.like;

import com.kartikey.blog.dto.like.LikeResponse;
import com.kartikey.blog.mapper.LikeMapper;
import com.kartikey.blog.model.Like;
import com.kartikey.blog.model.Post;
import com.kartikey.blog.model.User;
import com.kartikey.blog.repository.LikeRepo;
import com.kartikey.blog.repository.PostRepo;
import com.kartikey.blog.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{
    private final LikeRepo likeRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final LikeMapper likeMapper;

    @Override
    public String toggleLike(Long postId, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        Optional<Like> existingLike = likeRepo.findByUserAndPost(user, post);

        if (existingLike.isPresent()) {
            likeRepo.delete(existingLike.get());
            return "Post unliked";
        } else {
            Like like = Like.builder()
                    .user(user)
                    .post(post)
                    .build();
            likeRepo.save(like);
            return "Post liked";
        }
    }

    @Override
    public Long getLikeCount(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        return (long) likeRepo.countByPost(post);
    }

    @Override
    public List<LikeResponse> getLikesForPosts(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        return likeRepo.findAllByPost(post)
                .stream()
                .map(likeMapper::toDto)
                .toList();
    }
}
