package com.cmzn.authcontrol.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationFactory {
    Authentication getAuthorities(HttpServletRequest request);
}
