package com.xxxcloud.auth.service;

import com.xxxcloud.common.core.constants.ClaimsKeyConstant;
import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.sys.feign.ISysUserFeignClient;
import com.xxxcloud.service.api.sys.model.dto.LoginUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
public class SysLoginService {
    @Resource
    private ISysUserFeignClient sysUserFeignClient;

    /**
     * 登录
     */
    public LoginUser login(String account, String password) {
        // 查询用户信息
        R<LoginUser> userResult = sysUserFeignClient.getUserInfo(account, ClaimsKeyConstant.INNER);
        return new LoginUser();
    }


}
