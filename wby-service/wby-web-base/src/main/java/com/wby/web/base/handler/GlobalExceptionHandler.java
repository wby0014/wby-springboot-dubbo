package com.wby.web.base.handler;

import com.wby.common.core.api.CommonResult;
import com.wby.common.core.exception.BaseExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 全局异常处理
 * @Author wby
 * @Date 2020/3/28 16:41
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {

    /**
     * 登录异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class, AuthenticationException.class, AuthorizationException.class})
    public CommonResult handleAuthorizationException(AuthorizationException e) {
        log.error("handleAuthorizationException: {}", e.getMessage());
        return CommonResult.unauthorized(e.getMessage());
    }
}
