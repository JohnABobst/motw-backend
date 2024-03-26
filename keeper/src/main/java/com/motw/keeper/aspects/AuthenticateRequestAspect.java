package com.motw.keeper.aspects;

import com.motw.keeper.annotations.AuthenticateRequest;
import com.motw.keeper.services.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@AllArgsConstructor
public class AuthenticateRequestAspect {

    @Autowired
    private final AuthenticationService authenticationService;

    @Around("@annotation(authenticateRequest)")
    public Object authenticateBeforeRequest(ProceedingJoinPoint joinPoint, AuthenticateRequest authenticateRequest) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        String authHeader = request.getHeader("Authorization");
        ResponseEntity<?> validation = authenticationService.validateToken(authHeader);
        if (!validation.getStatusCode().is2xxSuccessful()) {
            response.sendError(401,"Token is not valid");
        }

        return joinPoint.proceed();
    }
}

