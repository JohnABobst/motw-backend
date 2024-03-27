package com.motw.keeper.services;

import com.netflix.discovery.converters.Auto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Value("${token.validation.url}")
    String authServiceUrl;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public ResponseEntity<?> validateToken(String authorizationHeader) {

        return webClientBuilder.build()
                .get()
                .uri(authServiceUrl)
                .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                .retrieve()
                .toBodilessEntity()
                .onErrorResume(throwable -> {
                    System.out.println(throwable.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
                })
                .block();
    }


}
