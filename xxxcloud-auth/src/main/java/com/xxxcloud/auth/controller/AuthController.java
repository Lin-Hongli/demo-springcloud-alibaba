package com.xxxcloud.auth.controller;

import com.xxxcloud.auth.params.LoginParams;
import com.xxxcloud.auth.service.SysLoginService;
import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.sys.model.dto.LoginUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AuthController {
    @Resource
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public R<?> login(@RequestBody LoginParams params) {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(params.getAccount(), params.getPassword());
        // 获取登录token
        //tokenService.createToken(userInfo)
        return R.ok("123123132123adaaddsa");
    }

}
