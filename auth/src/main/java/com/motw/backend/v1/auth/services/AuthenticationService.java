package com.motw.backend.v1.auth.services;

import com.motw.backend.v1.auth.dto.JwtAuthenticationResponse;
import com.motw.backend.v1.auth.dto.SignInRequest;
import com.motw.backend.v1.auth.dto.SignUpRequest;
import com.motw.backend.v1.auth.models.User;
import com.motw.backend.v1.auth.repositories.UserRepository;
import com.motw.backend.v1.auth.utils.JwTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.motw.backend.v1.auth.models.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserService userService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwTokenUtil jwTokenUtil;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse register(SignUpRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = User
                .builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(CharBuffer.wrap(request.getPassword())))
                .role(ROLE_USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        String jwt = jwTokenUtil.generateToken(user);

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse login(SignInRequest request) throws Exception {
        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        boolean isPasswordValid = passwordEncoder.matches(request.getPassword(), userDetails.getPassword());

        if (isPasswordValid) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } else {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(null, null));
        }

        User user;

        Optional<User> optUser = userRepository.findByUsername(request.getUsername());
        if (optUser.isPresent()) {
            user = optUser.get();
            String jwt = jwTokenUtil.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } else {
            throw new Exception("Unable to retrieve user");
        }
    }

    public Boolean validate(String token) {
        return jwTokenUtil.validateToken(token);
    }
}
