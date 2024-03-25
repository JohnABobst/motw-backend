package com.motw.backend.v1.auth.services;

import com.motw.backend.v1.auth.models.Role;
import com.motw.backend.v1.auth.models.User;
import com.motw.backend.v1.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.motw.backend.v1.auth.models.Role.ROLE_USER;

// AuthService.java
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public User createUser(User newUser) {
        // Check if user already exists
        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        newUser.setCreateAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        newUser.setRole(ROLE_USER);

        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
