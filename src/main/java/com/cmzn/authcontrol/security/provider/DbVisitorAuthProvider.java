package com.cmzn.authcontrol.security.provider;

import com.cmzn.authcontrol.entity.domain.RoleEntity;
import com.cmzn.authcontrol.security.AbstractVisitorAuthProvider;
import com.cmzn.authcontrol.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DbVisitorAuthProvider extends AbstractVisitorAuthProvider {

    @Autowired
    private RoleService roleService;
    @Autowired
    private GrantedAuthorityDefaults authorityDefaults;

    @Override
    public List<String> getDefaultAuths(HttpServletRequest request) {
        return super.getDefaultAuths(request);
    }

    @Override
    public List<String> getOwnAuths(HttpServletRequest request) {
        Long userId = 1L;
        String rolePrefix = authorityDefaults.getRolePrefix();
        List<RoleEntity> roles = roleService.getAvailableRolesByUserId(userId);
        return roles.stream().map(r -> rolePrefix + r.getMark()).collect(Collectors.toList());
    }
}
