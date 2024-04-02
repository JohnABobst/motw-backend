package com.motw.keeper.controllers;

import com.motw.keeper.dto.OpenAIResponse;
import com.motw.keeper.services.OpenAIService;
import com.motw.keeper.services.exceptions.OpenAIException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class KeeperController {

    @Autowired
    private final OpenAIService openAIService;

    @MessageMapping("/game/{gameId}")
    @SendTo("/topic/game/{gameId}")
    public String handleGameMessage(@DestinationVariable String gameId, String message) throws OpenAIException {
        OpenAIResponse response = openAIService.generateResponse(message);
        return response.getMessageAtIndex(0);
    }
}