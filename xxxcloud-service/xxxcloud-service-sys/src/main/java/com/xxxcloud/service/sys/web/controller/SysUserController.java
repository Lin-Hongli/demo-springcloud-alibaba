package com.xxxcloud.service.sys.web.controller;

import com.xxxcloud.service.sys.service.ISysUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LinHongli
 */
@RestController
@RequestMapping("/sys/user")
@Api("SysUserController")
public class SysUserController {
    @Resource
    private ISysUserService sysUserService;


}
