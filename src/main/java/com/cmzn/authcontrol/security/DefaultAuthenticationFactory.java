package com.cmzn.authcontrol.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultAuthenticationFactory implements AuthenticationFactory {

    private AuthProvider authProvider;

    public DefaultAuthenticationFactory(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    public Authentication getAuthorities(HttpServletRequest request) {
        List<String> auths = authProvider.getAuths(request);
        List<GrantedAuthority> authorities = auths.stream()
                .map(auth -> AuthControlGrantedAuthority.builder().auth(auth).build())
                .collect(Collectors.toList());
        return new AuthControlAuthenticationToken(authorities);
    }

    public void setAuthControlProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }
}
