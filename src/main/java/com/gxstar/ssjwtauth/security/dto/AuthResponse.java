package com.gxstar.ssjwtauth.security.dto;

public record AuthResponse(
        String username,
        String accessToken
) {
}
