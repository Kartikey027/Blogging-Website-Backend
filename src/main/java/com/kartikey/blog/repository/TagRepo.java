package com.kartikey.blog.repository;

import com.kartikey.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepo extends JpaRepository<Tag,Long> {
    Optional<Tag> findByName(String name);
    boolean existsByName(String name);
}
