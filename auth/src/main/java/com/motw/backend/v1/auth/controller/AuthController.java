package com.motw.backend.v1.auth.controller;

import com.motw.backend.v1.auth.dto.JwtAuthenticationResponse;
import com.motw.backend.v1.auth.dto.JwtValidateRequest;
import com.motw.backend.v1.auth.dto.SignInRequest;
import com.motw.backend.v1.auth.dto.SignUpRequest;
import com.motw.backend.v1.auth.model.User;
import com.motw.backend.v1.auth.service.AuthenticationService;
import com.motw.backend.v1.auth.service.UserService;
import com.motw.backend.v1.auth.utils.JwTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwTokenUtil jwTokenUtil;

    @Autowired
    private final UserService userService;

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public JwtAuthenticationResponse registerUser(@RequestBody SignUpRequest request) {
        // Create user account
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse authenticateUser(@RequestBody SignInRequest request) throws Exception {
        // Authenticate user
        return authenticationService.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        // Perform logout operation (if any)
        return ResponseEntity.ok("Logged out successfully");
    }
}
