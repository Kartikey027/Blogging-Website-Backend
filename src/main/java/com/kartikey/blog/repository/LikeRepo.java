package com.kartikey.blog.repository;

import com.kartikey.blog.model.Like;
import com.kartikey.blog.model.Post;
import com.kartikey.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepo extends JpaRepository<Like,Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
    int countByPost(Post post);
    List<Like> findAllByPost(Post post);
}
