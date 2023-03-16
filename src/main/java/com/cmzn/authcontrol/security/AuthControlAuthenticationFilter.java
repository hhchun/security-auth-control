package com.cmzn.authcontrol.security;

import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthControlAuthenticationFilter extends BasicAuthenticationFilter {

    private final AuthenticationFactory authenticationFactory;

    public AuthControlAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationFactory authenticationFactory) {
        super(authenticationManager);
        this.authenticationFactory = authenticationFactory;
    }

    public AuthControlAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint, AuthenticationFactory authenticationFactory) {
        super(authenticationManager, authenticationEntryPoint);
        this.authenticationFactory = authenticationFactory;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = authenticationFactory.getAuthorities(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
