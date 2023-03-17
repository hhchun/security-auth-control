package com.cmzn.authcontrol.auth;

import cn.hutool.extra.spring.SpringUtil;
import com.cmzn.authcontrol.common.utils.TokenUtils;
import org.redisson.RedissonShutdownException;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RedissonClient;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class UserContextHolder {
    public static final UserContext DEFAULT_USER_CONTEXT = new UserContext();

    private static final String CONTEXT_KEY = "USER_CONTEXT";
    private static final Lock CONTEXT_LOCK = new ReentrantLock();
    private static Map<String, UserContext> CONTEXT_MAP = null;
    private static final ThreadLocal<UserContext> THREAD_LOCAL_CONTEXT = new ThreadLocal<>();

    public static void setContext(String key, UserContext context) {
        if (CONTEXT_MAP == null) {
            throw new RedissonShutdownException("用户上下文没有初始化");
        }
        CONTEXT_MAP.put(key, context);
    }

    public static UserContext getContext(HttpServletRequest request) {
        UserContext context = THREAD_LOCAL_CONTEXT.get();
        if (context != null) {
            return context;
        }
        String token = request.getHeader("token");
        return getContext(token);
    }

    public static UserContext getContext(String token) {
        UserContext context = THREAD_LOCAL_CONTEXT.get();
        if (context != null) {
            return context;
        }
        if (!StringUtils.hasLength(token)) {
            return DEFAULT_USER_CONTEXT;
        }
        String userId = TokenUtils.getUserId(token);
        if (CONTEXT_MAP == null) {
            throw new RedissonShutdownException("用户上下文没有初始化");
        }
        context = CONTEXT_MAP.get(userId);
        if (context != null && context.getToken().equals(token)) {
            THREAD_LOCAL_CONTEXT.set(context);
            return context;
        } else {
            return DEFAULT_USER_CONTEXT;
        }
    }

    public static UserContext removeContext(String key) {
        if (CONTEXT_MAP == null) {
            throw new RedissonShutdownException("用户上下文没有初始化");
        }
        return CONTEXT_MAP.remove(key);
    }

    public static void init() {
        if (CONTEXT_MAP == null) {
            CONTEXT_LOCK.lock();
            try {
                if (CONTEXT_MAP == null) {
                    RedissonClient client = SpringUtil.getBean(RedissonClient.class);
                    LocalCachedMapOptions<String, UserContext> options = LocalCachedMapOptions.<String, UserContext>defaults()
                            .evictionPolicy(LocalCachedMapOptions.EvictionPolicy.LFU)
                            .cacheSize(1024)
                            .reconnectionStrategy(LocalCachedMapOptions.ReconnectionStrategy.LOAD)
                            .syncStrategy(LocalCachedMapOptions.SyncStrategy.INVALIDATE)
                            .timeToLive(1, TimeUnit.DAYS)
                            .maxIdle(1, TimeUnit.DAYS);
                    CONTEXT_MAP = client.getLocalCachedMap(CONTEXT_KEY, options);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RedissonShutdownException("用户上下文初始化异常");
            } finally {
                CONTEXT_LOCK.unlock();
            }
        }
    }
}
