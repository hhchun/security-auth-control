package com.cmzn.permission.security;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 权限提供者
 */
public interface PermissionProvider {
    List<String> getPermissions(HttpServletRequest request);
}
