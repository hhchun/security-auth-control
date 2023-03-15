package com.cmzn.permission.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求访问者的权限提供处理器
 */
public abstract class AbstractVisitorPermissionProvider implements PermissionProvider {
    @Autowired
    protected GrantedAuthorityDefaults defaults;

    @Override
    public List<String> getPermissions(HttpServletRequest request) {
        ArrayList<String> permissions = new ArrayList<>();
        List<String> dps = getDefaultPermissions(request);
        if (!CollectionUtils.isEmpty(dps)) {
            permissions.addAll(dps);
        }
        List<String> ops = getOwnPermissions(request);
        if (!CollectionUtils.isEmpty(ops)) {
            permissions.addAll(ops);
        }
        return permissions;
    }

    /**
     * 默认的权限
     *
     * @param request
     * @return
     */
    public abstract List<String> getDefaultPermissions(HttpServletRequest request);

    /**
     * 拥有的权限
     *
     * @param request
     * @return
     */
    public abstract List<String> getOwnPermissions(HttpServletRequest request);

}
