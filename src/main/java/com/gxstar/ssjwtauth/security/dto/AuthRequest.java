package com.gxstar.ssjwtauth.security.dto;

public record AuthRequest(
        String username,
        String password
) {
}
