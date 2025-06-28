package com.kartikey.blog.mapper;

import com.kartikey.blog.dto.post.PostRequest;
import com.kartikey.blog.dto.post.PostResponse;
import com.kartikey.blog.model.Post;
import com.kartikey.blog.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "author.username", target = "authorUsername")
    @Mapping(target = "likeCount", expression = "java(post.getLikes() != null ? post.getLikes().size() : 0)")
    @Mapping(target = "tags", expression = "java(post.getTags().stream().map(t -> t.getName()).collect(java.util.stream.Collectors.toList()))")
    PostResponse toDto(Post post);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "tags", source = "tags") // tell MapStruct to use mapToTags
    Post toPost(PostRequest request);

    // 👇 Helper mapping method
    default List<Tag> mapToTags(List<String> tagNames) {
        if (tagNames == null) return new ArrayList<>();
        return tagNames.stream()
                .map(name -> {
                    Tag tag = new Tag();
                    tag.setName(name);
                    return tag;
                })
                .collect(Collectors.toList());
    }
}
