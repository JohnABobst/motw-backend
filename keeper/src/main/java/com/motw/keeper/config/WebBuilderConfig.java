package com.motw.keeper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebBuilderConfig {
//    WebBuilder is asynchronous, you can put .block() at the end of the chain to make it wait for the response
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}

//Example of usage:
//{Model} is whatever Model or Object the endpoint is returning.  Webclient will automatically instantiate
// an object from the data in the body of the response.
//{Model} {Model} = webClient.get()
//        .uri(serviceAUrl + "/api/resource") Endpoint of Service A
//        .retrieve()
//        .bodyToMono({Model}.class)
//        .block(); Synchronous call, consider using asynchronous operations in production

