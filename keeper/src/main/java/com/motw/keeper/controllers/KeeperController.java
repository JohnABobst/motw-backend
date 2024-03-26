package com.motw.keeper.controllers;

import com.motw.keeper.annotations.AuthenticateRequest;
import com.motw.keeper.dto.KeeperResponse;
import com.motw.keeper.dto.PlayerInput;
import com.motw.keeper.services.AuthenticationService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class KeeperController {

    @AuthenticateRequest
    @PostMapping("/player-input")
    public KeeperResponse processPlayerInput(@RequestBody PlayerInput playerInput ) {
//        logic to make a call to the chatbot
        return new KeeperResponse(String.format("%s, was a good response", playerInput.getInput()));
    }
}
