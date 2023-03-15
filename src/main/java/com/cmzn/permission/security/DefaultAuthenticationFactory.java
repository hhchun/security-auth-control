package com.cmzn.permission.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultAuthenticationFactory implements AuthenticationFactory {

    private PermissionProvider permissionProvider;

    public DefaultAuthenticationFactory(PermissionProvider permissionProvider) {
        this.permissionProvider = permissionProvider;
    }

    @Override
    public Authentication getAuthorities(HttpServletRequest request) {
        List<String> permissions = permissionProvider.getPermissions(request);
        List<GrantedAuthority> authorities = permissions.stream()
                .map(permission -> PermissionGrantedAuthority.builder().permission(permission).build())
                .collect(Collectors.toList());
        return new PermissionAuthenticationToken(authorities);
    }

    public void setPermissionProvider(PermissionProvider permissionProvider) {
        this.permissionProvider = permissionProvider;
    }
}
