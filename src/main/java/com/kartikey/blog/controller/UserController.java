package com.kartikey.blog.controller;

import com.kartikey.blog.dto.user.UserDTO;
import com.kartikey.blog.dto.user.UserProfileResponse;
import com.kartikey.blog.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody UserDTO dto, @AuthenticationPrincipal UserDetails userDetails){
        userService.update(userDetails.getUsername(),dto);
        return ResponseEntity.ok("Profile Updated SuccessFully");
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(@AuthenticationPrincipal UserDetails userDetails){
        UserProfileResponse profile=userService.getProfile(userDetails.getUsername());
        return ResponseEntity.ok(profile);
    }

}
