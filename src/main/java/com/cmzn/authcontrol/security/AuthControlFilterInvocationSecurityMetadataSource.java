package com.cmzn.authcontrol.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public class AuthControlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private AuthProvider authProvider;

    public AuthControlFilterInvocationSecurityMetadataSource(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();
        List<String> auths = authProvider.getAuths(request);
        if (CollectionUtils.isEmpty(auths)) {
            throw new AccessDeniedException("[" + request.getRequestURI() + "]" + "没有配置访问权限");
        }
        return SecurityConfig.createList(auths.toArray(new String[0]));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void setAuthControlProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }
}
