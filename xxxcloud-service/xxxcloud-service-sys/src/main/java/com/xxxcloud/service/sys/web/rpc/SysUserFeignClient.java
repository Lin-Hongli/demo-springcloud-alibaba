package com.xxxcloud.service.sys.web.rpc;

import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.sys.feign.ISysUserFeignClient;
import com.xxxcloud.service.api.sys.model.dto.LoginUser;
import com.xxxcloud.service.sys.service.ISysUserService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 这里一般只负责调用对应的service
 * 多个service操作时，才做调用的逻辑处理
 *
 *
 * @author LinHongli
 */
@RestController
@Slf4j
public class SysUserFeignClient implements ISysUserFeignClient {
    @Resource
    private ISysUserService sysUserService;

    @Override
    public R<LoginUser> getUserInfo(String account, String source) {
        log.info("Seata全局事务id=================>{}", RootContext.getXID());
        int i=1/0;
        return R.ok();
    }
}
