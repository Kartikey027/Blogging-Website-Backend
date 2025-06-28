package com.kartikey.blog.mapper;

import com.kartikey.blog.dto.like.LikeResponse;
import com.kartikey.blog.model.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(source = "user.username",target = "likedBy")
    @Mapping(source = "post.id",target = "postId")
    LikeResponse toDto(Like like);
}
