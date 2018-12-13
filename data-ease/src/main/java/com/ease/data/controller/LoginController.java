package com.ease.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.ease.data.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param requestJson
     * @return
     */
    @PostMapping("/com/ease/data/auth")
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        return loginService.authLogin(requestJson);
    }

    /**
     * 查询当前登录用户的信息
     *
     * @return
     */
    @PostMapping("/getInfo")
    public JSONObject getInfo() {
        return loginService.getInfo();
    }

    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/logout")
    public JSONObject logout() {
        return loginService.logout();
    }
}