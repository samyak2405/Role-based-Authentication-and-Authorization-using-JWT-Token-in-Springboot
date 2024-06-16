package com.blogapp.userservice.controller;

import com.blogapp.userservice.dto.JwtAuthenticationResponse;
import com.blogapp.userservice.dto.RefreshTokenRequest;
import com.blogapp.userservice.dto.SignInRequest;
import com.blogapp.userservice.dto.SignUpRequest;
import com.blogapp.userservice.entity.User;
import com.blogapp.userservice.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest sign){
        log.info("Inside Sign up request");
        return ResponseEntity.ok(authenticationService.signUp(sign));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest sign){
        log.info("Inside Sign in Request");
        return ResponseEntity.ok(authenticationService.signIn(sign));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        log.info("Inside Refresh Request");
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
