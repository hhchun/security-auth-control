package com.cmzn.authcontrol.security;


import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

@Builder
public class AuthControlGrantedAuthority implements GrantedAuthority {

    private final String auth;

    public AuthControlGrantedAuthority(String auth) {
        this.auth = auth;
    }

    @Override
    public String getAuthority() {
        return this.auth;
    }
}
