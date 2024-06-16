package com.blogapp.userservice.repository;

import com.blogapp.userservice.entity.User;
import com.blogapp.userservice.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);

    User findByRole(Role role);
}
