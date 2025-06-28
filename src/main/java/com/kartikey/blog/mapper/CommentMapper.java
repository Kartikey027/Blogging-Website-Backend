package com.kartikey.blog.mapper;

import com.kartikey.blog.dto.comment.CommentRequest;
import com.kartikey.blog.dto.comment.CommentResponse;
import com.kartikey.blog.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "commenter.username", target = "commenterUsername")
    CommentResponse toDto(Comment comment);

    @Mapping(target = "commenter", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comment toEntity(CommentRequest request);
}