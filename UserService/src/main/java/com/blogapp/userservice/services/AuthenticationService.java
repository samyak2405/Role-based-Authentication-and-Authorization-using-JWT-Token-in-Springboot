package com.blogapp.userservice.services;

import com.blogapp.userservice.dto.JwtAuthenticationResponse;
import com.blogapp.userservice.dto.RefreshTokenRequest;
import com.blogapp.userservice.dto.SignInRequest;
import com.blogapp.userservice.dto.SignUpRequest;
import com.blogapp.userservice.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    public User signUp(SignUpRequest sign);
    public JwtAuthenticationResponse signIn(SignInRequest sign);

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
