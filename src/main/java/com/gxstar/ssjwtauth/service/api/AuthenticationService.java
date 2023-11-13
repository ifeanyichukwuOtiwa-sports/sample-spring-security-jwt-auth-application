package com.gxstar.ssjwtauth.service.api;

import com.gxstar.ssjwtauth.security.dto.AuthRequest;
import com.gxstar.ssjwtauth.security.dto.AuthResponse;

public interface AuthenticationService {
    AuthResponse authenticateAndLogin(AuthRequest authRequest);
}
