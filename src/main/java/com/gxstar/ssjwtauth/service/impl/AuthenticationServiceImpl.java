package com.gxstar.ssjwtauth.service.impl;

import com.gxstar.ssjwtauth.security.dto.AuthRequest;
import com.gxstar.ssjwtauth.security.dto.AuthResponse;
import com.gxstar.ssjwtauth.security.jwt.util.JwtTokenUtil;
import com.gxstar.ssjwtauth.security.model.SecurityUser;
import com.gxstar.ssjwtauth.service.api.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtUtil;

    @Override
    public AuthResponse authenticateAndLogin(final AuthRequest authRequest) {
        final Authentication auth = new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password());
        try {
            final Authentication authenticate = authenticationManager.authenticate(auth);
            final SecurityUser principal = (SecurityUser) authenticate.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(principal.getUser());
            return new AuthResponse(principal.getUsername(), accessToken);
        } catch (final Exception e) {
            throw new RuntimeException("Failed To Authenticate User");
        }
    }
}
