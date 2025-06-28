package com.kartikey.blog.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class PostRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String imageUrl;

    private List<String> tags;

    private boolean published;

}
