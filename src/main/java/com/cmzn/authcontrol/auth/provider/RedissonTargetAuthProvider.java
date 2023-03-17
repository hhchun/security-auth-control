package com.cmzn.authcontrol.auth.provider;

import com.cmzn.authcontrol.auth.TargetAuthCache;
import com.cmzn.authcontrol.security.AbstractTargetAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RedissonTargetAuthProvider extends AbstractTargetAuthProvider {

    @Autowired
    private TargetAuthCache targetAuthCache;
    @Autowired
    private GrantedAuthorityDefaults authorityDefaults;

    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public List<String> getAuths(HttpServletRequest request) {
        String rolePrefix = authorityDefaults.getRolePrefix();
        String uri = request.getRequestURI();
        Set<String> targets = targetAuthCache.getTargets();
        List<String> meetTargets  = targets.stream().filter(t -> matcher.match(t, uri)).collect(Collectors.toList());
        List<String> auths = targetAuthCache.getAuths(meetTargets);
        return auths.stream().map(a -> rolePrefix + a).collect(Collectors.toList());
    }
}
