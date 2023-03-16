package com.cmzn.authcontrol.security.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cmzn.authcontrol.entity.domain.AuthEntity;
import com.cmzn.authcontrol.entity.domain.RoleEntity;
import com.cmzn.authcontrol.entity.domain.RoleMtmAuthEntity;
import com.cmzn.authcontrol.security.AbstractTargetAuthProvider;
import com.cmzn.authcontrol.service.AuthService;
import com.cmzn.authcontrol.service.RoleMtmAuthService;
import com.cmzn.authcontrol.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DbTargetAuthProvider extends AbstractTargetAuthProvider {

    @Autowired
    private AuthService authService;
    @Autowired
    private RoleMtmAuthService roleMtmAuthService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GrantedAuthorityDefaults authorityDefaults;

    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public List<String> getAuths(HttpServletRequest request) {
        String rolePrefix = authorityDefaults.getRolePrefix();
        String uri = request.getRequestURI();
        List<AuthEntity> as = authService.list(new LambdaQueryWrapper<AuthEntity>()
                .select(AuthEntity::getId, AuthEntity::getSubject)
                .eq(AuthEntity::getStatus, 1));
        List<Long> aIds = as.stream().filter(a -> matcher.match(a.getSubject(), uri)).map(AuthEntity::getId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(aIds)) {
            return new ArrayList<>(1);
        }
        List<RoleMtmAuthEntity> ras = roleMtmAuthService.list(new LambdaQueryWrapper<RoleMtmAuthEntity>()
                .select(RoleMtmAuthEntity::getId, RoleMtmAuthEntity::getRoleId)
                .in(RoleMtmAuthEntity::getAuthId, aIds));
        if (CollectionUtils.isEmpty(ras)) {
            return new ArrayList<>(1);
        }
        List<Long> rIds = ras.stream().map(RoleMtmAuthEntity::getRoleId).collect(Collectors.toList());
        List<RoleEntity> rs = roleService.list(new LambdaQueryWrapper<RoleEntity>()
                .select(RoleEntity::getId, RoleEntity::getMark)
                .in(RoleEntity::getId, rIds)
                .eq(RoleEntity::getStatus, 1));
        return rs.stream().map(r -> rolePrefix + r.getMark()).collect(Collectors.toList());
    }
}
