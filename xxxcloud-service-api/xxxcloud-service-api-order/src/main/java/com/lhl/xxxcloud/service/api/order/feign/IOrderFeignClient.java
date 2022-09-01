package com.lhl.xxxcloud.service.api.order.feign;

import com.lhl.util.result.Result;
import com.lhl.xxxcloud.service.api.order.feign.hystrix.OrderFeignClientHystrix;
import com.lhl.xxxcloud.service.api.order.model.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 开放接口层：
 * 可直接封装 Service 方法暴露成 RPC 接口；
 * 通过 Web 封装成 http 接口；
 * 进行网关安全控制、流量控制等。
 *
 * @author LinHongli
 */
@FeignClient(value = "xxxcloud-service-order",fallback = OrderFeignClientHystrix.class)
public interface IOrderFeignClient {
    String API_PREFIX = "/order";

    @GetMapping("/test")
    Result<Object> test();

    /**
     * 创建订单
     * @param orderDTO Order传输对象
     * @return Result<Object>
     */
    @PostMapping(API_PREFIX+"/create")
    Result<Object> create(@RequestBody OrderDTO orderDTO);


    /**
     * 获取用户信息
     * @param id 唯一标识
     * @return Result<Object>
     */
    @GetMapping(API_PREFIX+"/getOrder")
    Result<Object> getOrder(@RequestParam("id") Long id);
}
