package com.gxstar.ssjwtauth.controller;

import com.gxstar.ssjwtauth.security.dto.AuthRequest;
import com.gxstar.ssjwtauth.security.dto.AuthResponse;
import com.gxstar.ssjwtauth.service.api.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody final AuthRequest authRequest) {
        return authService.authenticateAndLogin(authRequest);
    }
}
