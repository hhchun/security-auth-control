package com.cmzn.permission.demo;

import com.cmzn.permission.security.AbstractVisitorPermissionProvider;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
public class DemoVisitorPermissionProvider extends AbstractVisitorPermissionProvider {

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
