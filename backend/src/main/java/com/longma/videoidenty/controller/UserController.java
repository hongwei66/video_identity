package com.longma.videoidenty.controller;


import com.alibaba.fastjson.JSONObject;
import com.longma.videoidenty.common.BaseException;
import com.longma.videoidenty.common.SystemErrorEnum;
import com.longma.videoidenty.config.SysLogAno;
import com.longma.videoidenty.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    public final static Map<String, UserInfo> USERS = new HashMap<>();
    public static String curUserToken;

    /**
     * 登陆
     *
     * @param map
     * @return
     */
    @SysLogAno
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    public JSONObject login(@RequestBody Map<String, String> map) {

        // 检查用户名密码 及 获取token
        JSONObject res = new JSONObject();
        HashMap<String, String> m = new HashMap<String, String>();
        if (StringUtils.isEmpty(map.get("username")) || !map.get("username").equals("admin")) {
            throw new BaseException(SystemErrorEnum.SSO_AUTHORIZE_ERROR.getCode(), "用户名错误");
        }
        String token = map.get("username") + System.currentTimeMillis();
        m.put("token", token);
        res.put("data", m);
        res.put("code", 0);

        // 记录用户
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(map.get("username"));
        userInfo.setRoles(Arrays.asList("admin"));
        USERS.put(token, userInfo);
        curUserToken = token;
        return res;
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JSONObject getUserInfo(String token) {

        if (!USERS.containsKey(token)) {
            throw new BaseException(SystemErrorEnum.SYS_NOLOGIN_ERROR.getCode(), "未登录");
        }
        JSONObject res = new JSONObject();
        res.put("data", USERS.get(token));
        res.put("code", 0);
        res.put("msg", "success");
        return res;
    }
}