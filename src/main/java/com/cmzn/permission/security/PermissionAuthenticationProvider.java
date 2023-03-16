package com.cmzn.permission.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class PermissionAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (PermissionAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
