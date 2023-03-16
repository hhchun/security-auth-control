package com.cmzn.permission.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;

/**
 * 受保护目标需要具备的权限提供处理器
 */
public abstract class AbstractTargetPermissionProvider implements PermissionProvider {
}
