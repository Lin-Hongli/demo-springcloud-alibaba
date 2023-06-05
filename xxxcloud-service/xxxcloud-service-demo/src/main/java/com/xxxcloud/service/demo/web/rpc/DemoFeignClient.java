package com.xxxcloud.service.demo.web.rpc;

import com.xxxcloud.common.core.emuns.ErrorCodeEnum;
import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.demo.feign.IDemoFeignClient;
import com.xxxcloud.service.api.demo.model.entity.Demo;
import com.xxxcloud.service.demo.service.IDemoService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
public class DemoFeignClient implements IDemoFeignClient {
    @Resource
    private IDemoService demoService;

    @Override
    public R<Object> demo() {
        log.info("Seata全局事务id=================>{}", RootContext.getXID());
        return null;
    }
}
