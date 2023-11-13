package com.gxstar.ssjwtauth.security.service;

import com.gxstar.ssjwtauth.model.User;
import com.gxstar.ssjwtauth.security.model.SecurityUser;
import com.gxstar.ssjwtauth.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("Couldn't find user by username " + username));
        return SecurityUser.of(user);
    }
}
