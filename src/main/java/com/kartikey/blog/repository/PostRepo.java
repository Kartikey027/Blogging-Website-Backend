package com.kartikey.blog.repository;

import com.kartikey.blog.model.Post;
import com.kartikey.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post> findByAuthor(User author);
    List<Post> findByPublishedTrueOrderByCreatedAtDesc();
}
