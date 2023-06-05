package com.xxxcloud.service.api.sys.feign;

import com.xxxcloud.common.core.constants.ClaimsKeyConstant;
import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.sys.feign.hystrix.SysUserFeignClientHystrix;
import com.xxxcloud.service.api.sys.model.dto.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 *
 * @author LinHongli
 */
@FeignClient(value = "xxxcloud-service-sys",fallback = SysUserFeignClientHystrix.class)
public interface ISysUserFeignClient {
    String API_PREFIX = "/api/sys/user";
    /**
     * 通过用户名查询用户信息
     *
     * @param account 账号
     * @param source 请求来源
     * @return 结果
     */
    @GetMapping(API_PREFIX+"/info/{account}")
    R<LoginUser> getUserInfo(@PathVariable("account") String account, @RequestHeader(ClaimsKeyConstant.FROM_SOURCE) String source);
}
