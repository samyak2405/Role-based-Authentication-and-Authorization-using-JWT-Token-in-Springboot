package com.blogapp.userservice.services;

import com.blogapp.userservice.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public interface JWTService {

    public String extractUserName(String token);

    public String generateToken(UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> objectObjectHashMap, UserDetails user);
}
