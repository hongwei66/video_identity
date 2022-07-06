package com.longma.videoidenty.config;

import com.alibaba.fastjson.JSONObject;
import com.longma.videoidenty.common.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public JSONObject handleBizException(BaseException ex) {
        JSONObject res = new JSONObject();
        res.put("code", ex.getErrorCode());
        res.put("msg", ex.getErrorMsg());
        return res;
    }
}