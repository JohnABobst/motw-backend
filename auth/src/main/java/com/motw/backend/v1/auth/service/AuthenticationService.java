package com.motw.backend.v1.auth.service;

import com.motw.backend.v1.auth.dto.JwtAuthenticationResponse;
import com.motw.backend.v1.auth.dto.SignInRequest;
import com.motw.backend.v1.auth.dto.SignUpRequest;
import com.motw.backend.v1.auth.model.Role;
import com.motw.backend.v1.auth.model.User;
import com.motw.backend.v1.auth.repository.UserRepository;
import com.motw.backend.v1.auth.utils.JwTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        User user = User
                .builder()
                .username(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.createUser(user);
        String jwt = jwTokenUtil.generateToken(user);

        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public JwtAuthenticationResponse login(SignInRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        User user;

        Optional<User> optUser = userRepository.findByUsername(request.getUserName());
        if (optUser.isPresent()) {
            user = optUser.get();
            String jwt = jwTokenUtil.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();
        } else {
            throw new Exception("Unable to retrieve user");
        }
    }
}
