package com.motw.keeper.services;

import com.motw.keeper.dto.Message;
import com.motw.keeper.dto.OpenAIRequest;
import com.motw.keeper.dto.OpenAIResponse;
import com.motw.keeper.services.exceptions.OpenAIException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application-local.properties")
public class OpenAIService {

    @Value("${openai.secret.key}")
    private String secretKey;

    private String endpoint = "https://api.openai.com/v1/chat/completions";

    @Autowired
    private final RestTemplate restTemplate;

    public OpenAIResponse generateResponse(String message) throws OpenAIException {
        List<Message> messages = new ArrayList<>();

        Message messageDto = Message.builder().content(message).role("user").build();
        messages.add(messageDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(secretKey);

        OpenAIRequest request = new OpenAIRequest();
        request.setModel("gpt-3.5-turbo");
        request.setMessages(messages);

        HttpEntity<OpenAIRequest> requestEntity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<OpenAIResponse> responseEntity = restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    requestEntity,
                    OpenAIResponse.class
            );
            return responseEntity.getBody();
        } catch (Exception e) {
            throw new OpenAIException("Error occurred while sending your response to the chatbot: ", e);
        }
    }
}
