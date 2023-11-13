package com.gxstar.ssjwtauth.security.jwt.filter;

import com.gxstar.ssjwtauth.model.User;
import com.gxstar.ssjwtauth.security.jwt.util.JwtTokenUtil;
import com.gxstar.ssjwtauth.security.model.SecurityUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@NotNull final HttpServletRequest request,
                                    @NotNull final HttpServletResponse response,
                                    @NotNull final FilterChain filterChain) throws ServletException, IOException {

        if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = getAccessToken(request);

        if (!jwtTokenUtil.validateAccessToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        this.setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
    }

    private void setAuthenticationContext(final String token, final HttpServletRequest request) {
        final UserDetails userDetails = getUserDetails(token);
        final UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, null, null);

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private UserDetails getUserDetails(final String token) {
        final String[] it = jwtTokenUtil.getSubject(token).split(":");
        return SecurityUser.of(User.from(it[0], it[1], it[3], it[2]));
    }

    private String getAccessToken(final HttpServletRequest request) {
        final String header = request.getHeader("Authorization");
        return header.split(" ")[1].trim();
    }

    private boolean hasAuthorizationBearer(final HttpServletRequest request) {
        final String header = request.getHeader("Authorization");
        return !ObjectUtils.isEmpty(header) && header.startsWith("Bearer ");
    }
}
