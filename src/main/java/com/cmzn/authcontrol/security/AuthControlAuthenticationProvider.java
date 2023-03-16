package com.cmzn.authcontrol.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AuthControlAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (AuthControlAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
