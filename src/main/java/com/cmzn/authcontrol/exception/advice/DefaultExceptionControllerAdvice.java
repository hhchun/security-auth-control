package com.cmzn.authcontrol.exception.advice;

import com.cmzn.authcontrol.common.constant.ResultCodeConstant;
import com.cmzn.authcontrol.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
@Order
public class DefaultExceptionControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public R<?> defaultRuntimeExceptionHandler(RuntimeException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error(ResultCodeConstant.ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public R<?> defaultExceptionHandler(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error(ResultCodeConstant.ERROR.getCode(), e.getMessage());
    }
}
