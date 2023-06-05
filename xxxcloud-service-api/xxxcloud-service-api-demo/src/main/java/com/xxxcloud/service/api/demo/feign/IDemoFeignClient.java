package com.xxxcloud.service.api.demo.feign;

import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.api.demo.feign.hystrix.DemoFeignClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 开放接口层：
 * 可直接封装 Service 方法暴露成 RPC 接口；
 * 通过 Web 封装成 http 接口；
 * 进行网关安全控制、流量控制等。
 *
 * @author LinHongli
 */
@FeignClient(value = "xxxcloud-service-demo",fallback = DemoFeignClientHystrix.class)
public interface IDemoFeignClient {
    String API_PREFIX = "/api/demo";

    @GetMapping("/test")
    R<Object> demo();
}
