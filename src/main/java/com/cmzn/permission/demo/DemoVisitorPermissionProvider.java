package com.cmzn.permission.demo;

import com.cmzn.permission.security.AbstractVisitorPermissionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
public class DemoVisitorPermissionProvider extends AbstractVisitorPermissionProvider {
    @Autowired
    protected GrantedAuthorityDefaults defaults;
    @Override
    public List<String> getDefaultPermissions(HttpServletRequest request) {
        return Arrays.asList(defaults.getRolePrefix() + "USER");
    }

    @Override
    public List<String> getOwnPermissions(HttpServletRequest request) {
        return Arrays.asList(defaults.getRolePrefix() + "ADMIN");
//        return null;
    }
}
