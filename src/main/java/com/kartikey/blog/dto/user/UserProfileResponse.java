package com.kartikey.blog.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileResponse {
    private String username;
    private String email;
    private String bio;
    private String profileImageUrl;

}
