package com.cmzn.permission.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class PermissionFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private PermissionProvider permissionProvider;


    public PermissionFilterInvocationSecurityMetadataSource(PermissionProvider permissionProvider) {
        this.permissionProvider = permissionProvider;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        List<String> permissions = permissionProvider.getPermissions(request);
        if (CollectionUtils.isEmpty(permissions)) {
            throw new IllegalArgumentException("[" + request.getRequestURI() + "]" + "没有配置访问权限");
        }
        return SecurityConfig.createList(permissions.get(0));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void setPermissionProvider(PermissionProvider permissionProvider) {
        this.permissionProvider = permissionProvider;
    }
}
