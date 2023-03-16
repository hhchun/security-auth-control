package com.cmzn.permission.demo;

import com.cmzn.permission.security.AbstractTargetPermissionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
public class DemoTargetPermissionProvider extends AbstractTargetPermissionProvider {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    protected GrantedAuthorityDefaults defaults;

    @Override
    public List<String> getPermissions(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (antPathMatcher.match("/demo/test/**", uri)) {
            return Arrays.asList(defaults.getRolePrefix() + "ADMIN");
        }else {
            return Arrays.asList(defaults.getRolePrefix() + "USER");
        }
    }
}
