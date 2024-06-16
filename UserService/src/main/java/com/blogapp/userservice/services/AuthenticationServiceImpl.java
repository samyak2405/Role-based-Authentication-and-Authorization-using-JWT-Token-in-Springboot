package com.blogapp.userservice.services;

import com.blogapp.userservice.dto.JwtAuthenticationResponse;
import com.blogapp.userservice.dto.RefreshTokenRequest;
import com.blogapp.userservice.dto.SignInRequest;
import com.blogapp.userservice.dto.SignUpRequest;
import com.blogapp.userservice.entity.User;
import com.blogapp.userservice.enums.Role;
import com.blogapp.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;
    public User signUp(SignUpRequest sign){
        log.info("Inside sign up request");
        User user = User.builder()
                .firstName(sign.getFirstName())
                .lastName(sign.getLastName())
                .email(sign.getEmail())
                .role(Role.ADMIN)
                .password(passwordEncoder.encode(sign.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signIn(SignInRequest sign){
        log.info("Inside sign in request");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sign.getEmail(),sign.getPassword()));
        var user = userRepository.findByEmail(sign.getEmail()).orElseThrow(()->
                new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        log.info("Inside request token");
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(()->new UsernameNotFoundException("User not found"));
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
