package com.cmzn.authcontrol.auth.provider;

import com.cmzn.authcontrol.auth.UserContextHolder;
import com.cmzn.authcontrol.auth.UserContext;
import com.cmzn.authcontrol.security.AbstractVisitorAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RedissonVisitorAuthProvider extends AbstractVisitorAuthProvider {
    @Autowired
    private GrantedAuthorityDefaults authorityDefaults;

    @Override
    public List<String> getDefaultAuths(HttpServletRequest request) {
        return Collections.singletonList("ROLE_DEF");
    }

    @Override
    public List<String> getOwnAuths(HttpServletRequest request) {
        String rolePrefix = authorityDefaults.getRolePrefix();
        UserContext context = UserContextHolder.getContext(request);
        List<UserContext.Role> roles = context.getRoles();
        return roles.stream().map(r -> rolePrefix + r.getMark()).collect(Collectors.toList());
    }
}
