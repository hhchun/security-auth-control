package com.cmzn.permission.security;


import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

@Builder
public class PermissionGrantedAuthority implements GrantedAuthority {

    private final String permission;

    public PermissionGrantedAuthority(String permission) {
        this.permission = permission;
    }

    @Override
    public String getAuthority() {
        return this.permission;
    }
}
