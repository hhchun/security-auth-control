package com.cmzn.authcontrol.exception.advice;

import com.cmzn.authcontrol.common.constant.ResultCodeConstant;
import com.cmzn.authcontrol.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import javax.servlet.http.HttpServletRequest;

/**
 * 权限相关的异常处理器
 */
@Slf4j
@RestControllerAdvice
@Order(Integer.MAX_VALUE - 1)
public class SecurityExceptionControllerAdvice {

    @ExceptionHandler(value = AccessDeniedException.class)
    public R<?> accessDeniedExceptionHandler(AccessDeniedException e, HttpServletRequest request) {
        log.info(e.getMessage());
        log.info("请求路径：{}", request.getRequestURI());
        log.info(ResultCodeConstant.PERMISSION.getMessage());
        return R.error(ResultCodeConstant.PERMISSION.getCode(), ResultCodeConstant.PERMISSION.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public R<?> authenticationExceptionHandler(AuthenticationException e, HttpServletRequest request) {
        log.info(e.getMessage());
        log.info("请求路径：{}", request.getRequestURI());
        log.info(ResultCodeConstant.PERMISSION.getMessage());
        return R.error(ResultCodeConstant.PERMISSION.getCode(), ResultCodeConstant.PERMISSION.getMessage());
    }
}
