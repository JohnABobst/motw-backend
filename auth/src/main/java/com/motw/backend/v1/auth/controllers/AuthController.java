package com.motw.backend.v1.auth.controllers;

import com.motw.backend.v1.auth.dto.JwtAuthenticationResponse;
import com.motw.backend.v1.auth.dto.SignInRequest;
import com.motw.backend.v1.auth.dto.SignUpRequest;
import com.motw.backend.v1.auth.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public JwtAuthenticationResponse registerUser(@RequestBody SignUpRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse authenticateUser(@RequestBody SignInRequest request) throws Exception {
        return authenticationService.login(request);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken() {
        return ResponseEntity.ok("Token is valid");
    }
}
