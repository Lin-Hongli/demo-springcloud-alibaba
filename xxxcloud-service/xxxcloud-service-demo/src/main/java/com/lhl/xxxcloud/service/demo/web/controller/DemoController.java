package com.lhl.xxxcloud.service.demo.web.controller;

import com.lhl.util.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * @author LinHongli
 */
@RestController
@RequestMapping("/demo")
@Api("非restful风格的controller")
public class DemoController {
    //@Resource
    //IOrderFeignClient orderFeignClient;

    @ApiOperation(value = "根据id查询(接口说明)", httpMethod = "GET(接口请求方式)", response = Result.class, notes = "1.0.0(接口发布说明)")
    @ApiParam(required = true, name = "id(参数名称)", value = "这是一个id(参数具体描述)")
    @GetMapping(path = "/info")
    public Result<Object> info(@RequestParam(name = "id") @NotEmpty(message = "id不能为空") String id) {
        //return orderFeignClient.getOrder(Long.valueOf(id));
        return null;
    }



}
