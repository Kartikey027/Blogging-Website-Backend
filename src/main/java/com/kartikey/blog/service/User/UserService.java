package com.kartikey.blog.service.User;

import com.kartikey.blog.dto.user.UserDTO;
import com.kartikey.blog.dto.user.UserProfileResponse;
import com.kartikey.blog.model.User;
import com.kartikey.blog.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public void update(String username, UserDTO dto){
        User user = userRepo.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("User Not Found"));
        if (dto.getBio() !=null){
            user.setBio(dto.getBio());
        }
        if (dto.getProfileImageUrl() != null){
            user.setProfileImageUrl(dto.getProfileImageUrl());
        }
        userRepo.save(user);
    }

    public UserProfileResponse getProfile(String username){
        User user= userRepo.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Username Not Found"));
        return new UserProfileResponse(
                user.getUsername(),
                user.getEmail(),
                user.getBio(),
                user.getProfileImageUrl()
        );

    }
}
