package com.cmzn.authcontrol.security;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 权限提供者
 */
public interface AuthProvider {
    List<String> getAuths(HttpServletRequest request);
}
