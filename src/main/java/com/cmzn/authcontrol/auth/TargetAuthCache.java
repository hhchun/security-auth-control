package com.cmzn.authcontrol.auth;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cmzn.authcontrol.entity.bo.AuthBo;
import com.cmzn.authcontrol.entity.bo.RoleBo;
import com.cmzn.authcontrol.entity.domain.AuthEntity;
import com.cmzn.authcontrol.entity.domain.RoleEntity;
import com.cmzn.authcontrol.entity.domain.RoleMtmAuthEntity;
import com.cmzn.authcontrol.service.AuthService;
import com.cmzn.authcontrol.service.RoleMtmAuthService;
import com.cmzn.authcontrol.service.RoleService;
import jodd.util.ArraysUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.redisson.RedissonShutdownException;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TargetAuthCache implements ApplicationListener<ApplicationReadyEvent> {
    private static final String TARGET_AUTH_KEY = "TARGET_AUTH_KEY";
    private static final Lock TARGET_AUTH_LOCK = new ReentrantLock();
    private Map<String, List<String>> TARGET_AUTH_MAP = null;

    @Autowired
    private AuthService authService;


    public Set<String> getTargets() {
        if (TARGET_AUTH_MAP == null) {
            throw new RedissonShutdownException("权限控制缓存没有初始化");
        }
        return TARGET_AUTH_MAP.keySet();
    }

    public List<String> getAuths(String target) {
        if (TARGET_AUTH_MAP == null) {
            throw new RedissonShutdownException("权限控制缓存没有初始化");
        }
        List<String> auths = TARGET_AUTH_MAP.get(target);
        if (auths == null) {
            // TODO 查询数据刷新
            return new ArrayList<>();
        }
        return auths;
    }

    public List<String> getAuths(List<String> targets) {
        if (TARGET_AUTH_MAP == null) {
            throw new RedissonShutdownException("权限控制缓存没有初始化");
        }
        if (CollectionUtils.isEmpty(targets)) {
            return new ArrayList<>();
        }
       return targets.stream().flatMap(t -> getAuths(t).stream()).collect(Collectors.toList());
    }

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        if (TARGET_AUTH_MAP != null) {
            return;
        }
        TARGET_AUTH_LOCK.lock();
        try {
            if (TARGET_AUTH_MAP == null) {
                RedissonClient client = SpringUtil.getBean(RedissonClient.class);
                LocalCachedMapOptions<String, List<String>> options = LocalCachedMapOptions.<String, List<String>>defaults()
                        .evictionPolicy(LocalCachedMapOptions.EvictionPolicy.LFU)
                        .cacheSize(1024)
                        .reconnectionStrategy(LocalCachedMapOptions.ReconnectionStrategy.LOAD)
                        .syncStrategy(LocalCachedMapOptions.SyncStrategy.INVALIDATE)
                        .timeToLive(1, TimeUnit.DAYS)
                        .maxIdle(1, TimeUnit.DAYS);
                TARGET_AUTH_MAP = client.getLocalCachedMap(TARGET_AUTH_KEY, options);
                if (TARGET_AUTH_MAP.size() == 0) {
                    refresh();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RedissonShutdownException("权限控制缓存没有初始化");
        } finally {
            TARGET_AUTH_LOCK.unlock();
        }
    }

    public void refresh() {
        if (TARGET_AUTH_MAP == null) {
            throw new RedissonShutdownException("权限控制缓存没有初始化");
        }
        List<AuthBo> auths = authService.getAuths();
        if (CollectionUtils.isEmpty(auths)) {
            return;
        }
        Map<String, List<String>> authMap = auths.stream().collect(Collectors.toMap(
                AuthBo::getSubject,
                a -> a.getRoles().stream().map(RoleBo::getMark).collect(Collectors.toList())
        ));

        TARGET_AUTH_MAP.putAll(authMap);
    }
}
