package com.blogapp.userservice.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
    public UserDetailsService userDetailsService();


}
