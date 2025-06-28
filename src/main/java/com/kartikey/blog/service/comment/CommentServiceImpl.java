package com.kartikey.blog.service.comment;

import com.kartikey.blog.dto.comment.CommentRequest;
import com.kartikey.blog.dto.comment.CommentResponse;
import com.kartikey.blog.mapper.CommentMapper;
import com.kartikey.blog.model.Comment;
import com.kartikey.blog.model.Post;
import com.kartikey.blog.model.User;
import com.kartikey.blog.repository.CommentRepo;
import com.kartikey.blog.repository.PostRepo;
import com.kartikey.blog.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepo commentRepo;
    private final UserRepo userRepo;
    private final PostRepo postRepo;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponse addComment(Long postId, CommentRequest request,String username){
        Post post=postRepo.findById(postId)
                .orElseThrow(()->new RuntimeException("Post Not Found"));
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User Not Found"));
        Comment comment=commentMapper.toEntity(request);

        comment.setPost(post);
        comment.setCommenter(user);
        comment.setCreatedAt(LocalDateTime.now());

        return commentMapper.toDto(commentRepo.save(comment));
    }

    @Override
    public List<CommentResponse> getCommentsByPostId(Long postId){
        List<Comment> comments=commentRepo.findByPostId(postId);
        return comments.stream().map(commentMapper::toDto).toList();
    }

    @Override
    public void deleteComment(Long commentId,String username){
        Comment comment=commentRepo.findById(commentId)
                .orElseThrow(()->new RuntimeException("Comment Not Found"));
        if(!comment.getCommenter().getUsername().equals(username)){
            throw new AccessDeniedException("You can only delete your own comments");
        }
        commentRepo.delete(comment);
    }
}
