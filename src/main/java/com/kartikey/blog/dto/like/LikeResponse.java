package com.kartikey.blog.dto.like;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LikeResponse {
    private Long postId;
    private String likedBy;
    private LocalDateTime likedAt;
}
