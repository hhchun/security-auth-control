package com.cmzn.permission.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationFactory {
    Authentication getAuthorities(HttpServletRequest request);
}
