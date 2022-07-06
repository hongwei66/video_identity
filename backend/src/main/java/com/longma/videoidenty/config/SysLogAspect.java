package com.longma.videoidenty.config;


import com.longma.videoidenty.common.BaseException;
import com.longma.videoidenty.common.SystemErrorEnum;
import com.longma.videoidenty.model.SysLog;
import com.longma.videoidenty.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
@Slf4j
public class SysLogAspect {

    @Pointcut("@annotation(com.longma.videoidenty.config.SysLogAno)")
    public void controllerLog() {}

    @Before("controllerLog()")
    public void before(JoinPoint joinPoint) throws Throwable {
        Date time = new Date(System.currentTimeMillis());
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            SysLog sysLogBO = new SysLog();
            sysLogBO.setStartTime(time);
            SysLogAno sysLogAno = method.getAnnotation(SysLogAno.class);
            if(sysLogAno != null){
                //注解上的描述
                sysLogBO.setRemark(sysLogAno.value());
            }
            //请求的 类名、方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = signature.getName();
            sysLogBO.setClassName(className);
            sysLogBO.setMethodName(methodName);
            //请求的参数
            Object[] args = joinPoint.getArgs();
            sysLogBO.setParams(Arrays.toString(args));
//            try{
//                List<String> list = new ArrayList<String>();
//                for (Object o : args) {
//                    JSONObject json = (JSONObject) JSON.toJSON(o);
//                    list.add(json.toString());
//                }
//
//            }catch (Exception e){
//                log.error(CommonUtil.getStackTrace(e));
//                throw new BaseException(SystemErrorEnum.LOG_ERROR);
//            }
            log.info("controller req log: {}", sysLogBO);
        } catch (Exception e) {
            log.error(CommonUtil.getStackTrace(e));
            throw new BaseException(SystemErrorEnum.LOG_ERROR);
        }
    }
}
