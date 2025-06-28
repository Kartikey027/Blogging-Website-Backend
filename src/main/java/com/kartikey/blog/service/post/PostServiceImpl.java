package com.kartikey.blog.service.post;

import com.kartikey.blog.dto.post.PostRequest;
import com.kartikey.blog.dto.post.PostResponse;
import com.kartikey.blog.mapper.PostMapper;
import com.kartikey.blog.model.Post;
import com.kartikey.blog.model.Tag;
import com.kartikey.blog.model.User;
import com.kartikey.blog.repository.PostRepo;
import com.kartikey.blog.repository.TagRepo;
import com.kartikey.blog.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final PostMapper postMapper;
    private final TagRepo tagRepo;

    @Override
    public PostResponse createPost(PostRequest request, String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Tag> tags = request.getTags().stream()
                .map(tagName -> tagRepo.findByName(tagName)
                        .orElseGet(() -> tagRepo.save(new Tag(null, tagName)))) // Save new tag if doesn't exist
                .collect(Collectors.toList());

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(user);
        post.setTags(tags); // ✅ Now tags are all managed/persisted
        post.setPublished(request.isPublished());
        post.setCreatedAt(LocalDateTime.now());

        Post savedPost = postRepo.save(post);
        return postMapper.toDto(savedPost);
    }


    @Override
    public List<PostResponse> getAllPublishedPost(){
        return postRepo.findByPublishedTrueOrderByCreatedAtDesc()
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePost(Long id,String username){
        Post post= postRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Post not Found"));
        if (!post.getAuthor().getUsername().equals(username)){
            throw new RuntimeException("You are not the Author of this Post");
        }
        postRepo.delete(post);
    }

    @Override
    public PostResponse getPostById(Long id){
        Post post=postRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Post Not Found"));
        return postMapper.toDto(post);
    }

    @Override
    public PostResponse updatePost(Long id, PostRequest request,String username){
        Post post= postRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Post Not Found"));
        if (!post.getAuthor().getUsername().equals(username)){
            throw new RuntimeException("You are not the the author of post");
        }
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setPublished(request.isPublished());
        post.setImageUrl(request.getImageUrl());

        Post updated=postRepo.save(post);

        return postMapper.toDto(updated);
    }


}
