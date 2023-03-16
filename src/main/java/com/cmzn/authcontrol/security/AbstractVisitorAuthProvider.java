package com.cmzn.authcontrol.security;

import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求访问者的权限提供处理器
 */
public abstract class AbstractVisitorAuthProvider implements AuthProvider {

    @Override
    public List<String> getAuths(HttpServletRequest request) {
        ArrayList<String> auths = new ArrayList<>();
        List<String> dps = getDefaultAuths(request);
        if (!CollectionUtils.isEmpty(dps)) {
            auths.addAll(dps);
        }
        List<String> ops = getOwnAuths(request);
        if (!CollectionUtils.isEmpty(ops)) {
            auths.addAll(ops);
        }
        return auths;
    }

    /**
     * 默认的权限
     *
     * @param request
     * @return
     */
    public List<String> getDefaultAuths(HttpServletRequest request){
        return new ArrayList<>();
    }

    /**
     * 拥有的权限
     *
     * @param request
     * @return
     */
    public abstract List<String> getOwnAuths(HttpServletRequest request);

}
