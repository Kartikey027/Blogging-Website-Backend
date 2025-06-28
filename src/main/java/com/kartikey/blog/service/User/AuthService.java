package com.kartikey.blog.service.User;

import com.kartikey.blog.dto.auth.AuthResponse;
import com.kartikey.blog.dto.auth.LoginRequest;
import com.kartikey.blog.dto.auth.RegisterRequest;
import com.kartikey.blog.model.User;
import com.kartikey.blog.repository.UserRepo;
import com.kartikey.blog.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        User user= userRepo.findByUsername(request.getUsername())
                .orElseThrow(()->new RuntimeException("User Not Found"));

        String token=jwtUtil.generateToken(user.getUsername());

        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest request){
        if (userRepo.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Username already taken");
        }
         User newUser= User.builder()
                 .username(request.getUsername())
                 .password(passwordEncoder.encode(request.getPassword()))
                 .email(request.getEmail())
                 .bio(request.getBio())
                 .build();
        userRepo.save(newUser);
         String token= jwtUtil.generateToken(newUser.getUsername());

         return new AuthResponse(token);
    }
}
