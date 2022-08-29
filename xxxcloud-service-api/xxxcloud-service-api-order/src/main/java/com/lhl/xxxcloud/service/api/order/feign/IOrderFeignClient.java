package com.lhl.xxxcloud.service.api.order.feign;

import com.lhl.util.result.Result;
import com.lhl.xxxcloud.service.api.order.feign.hystrix.OrderFeignClientHystrix;
import com.lhl.xxxcloud.service.api.order.model.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 开放接口层：
 * 可直接封装 Service 方法暴露成 RPC 接口；
 * 通过 Web 封装成 http 接口；
 * 进行网关安全控制、流量控制等。
 *
 * @author LinHongli
 */
@FeignClient(value = "demo-service-order",fallback = OrderFeignClientHystrix.class)
public interface IOrderFeignClient {
    String API_PREFIX = "/api/order";

    /**
     * 获取用户信息
     * @param id 唯一标识
     * @return Result<Object>
     */
    @GetMapping(API_PREFIX+"/getOrder")
    Result<Object> getOrder(@RequestParam("id") Long id);

    /**
     * 保存
     * @param orderDTO Order传输对象
     * @return Result<Object>
     */
    @GetMapping(API_PREFIX+"/save")
    Result<Object> save(OrderDTO orderDTO);
}
