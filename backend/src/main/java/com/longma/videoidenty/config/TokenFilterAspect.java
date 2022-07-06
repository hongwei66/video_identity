package com.longma.videoidenty.config;

import com.longma.videoidenty.common.BaseException;
import com.longma.videoidenty.common.SystemErrorEnum;
import com.longma.videoidenty.controller.UserController;
import com.longma.videoidenty.model.SysLog;
import com.longma.videoidenty.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Sets;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Component
@Aspect
@Slf4j
public class TokenFilterAspect {

    @Pointcut("execution(* com.longma.videoidenty.controller..*(..)) " +
                "&& !execution(* com.longma.videoidenty.controller.WebsocketServer.*(..))")
    public void controllerTokenFilter() {}

    @Before("controllerTokenFilter()")
    public void before(JoinPoint joinPoint) throws Throwable {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = request.getParameter("token");
            String uri = request.getRequestURI();
            log.info("token: {}, {}", token, uri);
            if (!(uri.contains("push") || uri.contains("login")) && !UserController.USERS.containsKey(token) ) {
                throw new BaseException(SystemErrorEnum.SYS_NOLOGIN_ERROR);
            }
    }
}
