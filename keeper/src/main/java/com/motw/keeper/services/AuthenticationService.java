package com.motw.keeper.services;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthenticationService {

    @Autowired
    private WebClient.Builder webClientBuilder;


}
