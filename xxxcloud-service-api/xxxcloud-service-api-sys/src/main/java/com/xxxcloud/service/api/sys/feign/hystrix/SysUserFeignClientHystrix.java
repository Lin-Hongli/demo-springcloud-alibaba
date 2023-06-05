package com.xxxcloud.service.api.sys.feign.hystrix;

import com.xxxcloud.common.core.emuns.ErrorCodeEnum;
import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.sys.feign.ISysUserFeignClient;
import com.xxxcloud.service.api.sys.model.dto.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author LinHongli
 */
@Slf4j
@Component
public class SysUserFeignClientHystrix implements ISysUserFeignClient {

    @Override
    public R<LoginUser> getUserInfo(String account, String source) {
        return R.fail(ErrorCodeEnum.B0200);
    }
}
