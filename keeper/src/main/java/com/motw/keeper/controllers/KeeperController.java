package com.motw.keeper.controllers;

import com.motw.keeper.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
@RequiredArgsConstructor
public class KeeperController {

    @Autowired
    private final AuthenticationService authenticationService;

    @GetMapping("/test/validation")
    public ResponseEntity<?> testTokenValidation(@RequestHeader("Authorization") String token ) {
        return authenticationService.validateToken(token);
    }
}
