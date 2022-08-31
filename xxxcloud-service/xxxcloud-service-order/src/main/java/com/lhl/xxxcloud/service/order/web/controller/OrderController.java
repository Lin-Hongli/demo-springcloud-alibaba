package com.lhl.xxxcloud.service.order.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhl.util.result.Result;
import com.lhl.xxxcloud.base.emuns.BusinessErrorCodeEnum;
import com.lhl.xxxcloud.base.exception.BusinessException;
import com.lhl.xxxcloud.service.api.order.model.vo.OrderVO;
import com.lhl.xxxcloud.service.order.model.entity.Order;
import com.lhl.xxxcloud.service.order.model.query.OrderQuery;
import com.lhl.xxxcloud.service.order.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Web 层：主要是对访问控制进行转发，各类基本参数校验，或者不复用的业务简单处理等。
 * #统一返回Result对象
 * 所有函数返回统一的Result/PageResult格式，没有统一格式，AOP无法玩，更加重要的是前台代码很不好写。
 * #Result不允许往后传
 * Result/PageResult是controller专用的，不允许往后传！往其他地方传之后，可读性立马下降，和传map，json好不了多少。
 * #Controller只做参数格式的转换
 * Controller做参数格式的转换，不允许把json，map这类对象传到services去，也不允许services返回json、map。
 * 写过代码都知道，map，json这种格式灵活，但是可读性差（ 编码一时爽，重构火葬场）。
 * 如果放业务数据，每次阅读起来都十分困难，需要从头到尾看完才知道里面有什么，是什么格式。
 * 定义一个bean看着工作量多了，但代码清晰多了。
 * #参数不允许出现Request，Response 这些对象,和json/map一样，主要是可读性差的问题。一般情况下不允许出现这些参数，除非要操作流。
 * #不需要打印日志,日志在AOP里面会打印，建议是大部分日志在Services这层打印。
 * ###建议
 * Contorller只做参数格式转换，如果没有参数需要转换的，那么就一行代码。日志/参数校验/权限判断建议放到service里面，毕竟controller基本无法重用，而service重用较多。而我们的单元测试也不需要测试controller，直接测试service即可。
 *
 * @author LinHongli
 */
@RestController
@RequestMapping("/order")
@Api("非restful风格的controller")
public class OrderController {
    @Resource
    private IOrderService orderService;

    @GetMapping("/payOrder")
    @ApiOperation(value = "支付订单", notes = "支付订单")
    public Result<Object> payOrder(Integer num) {
        return orderService.payOrder(num);
    }


    /**
     * 新增
     *
     * 有通用异常处理，参数列表不需要跟BindingResult bindingResult
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增", notes = "传入orderQuery")
    public Result<Object> save(@Validated({OrderQuery.Save.class}) @RequestBody OrderQuery orderQuery) {
        Order order=new Order();
        BeanUtils.copyProperties(orderQuery,order);
        return Result.success(orderService.save(order));
    }

    /**
     * 删除
     */
    @PostMapping(value = "/remove")
    @ApiOperation(value = "删除", notes = "传入orderQuery")
    public Result<Object> remove(@RequestParam(name = "id") @NotEmpty(message = "id不能为空") String id) {
//        Func.toLongList(ids)
        return Result.success(orderService.removeById(Long.valueOf(id)));
    }

    /**
     * 修改
     */
    @PostMapping("/modify")
    @ApiOperation(value = "修改", notes = "传入orderQuery")
    public Result<Object> modify(@Validated(OrderQuery.Update.class) @RequestBody OrderQuery orderQuery) {
        Order order=new Order();
        BeanUtils.copyProperties(orderQuery,order);
        order.setId(Long.valueOf(orderQuery.getId()));

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Order::getId,order.getId());
        boolean update = orderService.update(order, queryWrapper);
        if(!update){
            throw new BusinessException(BusinessErrorCodeEnum.USER10010003,order.getId());
        }
        return Result.success();
    }

    /**
     * 详情
     *
     * URL 路径不能使用大写 单词如果需要分隔，统一使用下划线。
     */
    @ApiOperation(value = "根据id查询(接口说明)", httpMethod = "GET(接口请求方式)", response = Result.class, notes = "1.0.0(接口发布说明)")
    @ApiParam(required = true, name = "id(参数名称)", value = "这是一个id(参数具体描述)")
    @GetMapping(path = "/query")
    public Result<Object> query(@RequestParam(name = "id") @NotEmpty(message = "id不能为空") String id) {
        Order order = orderService.getById(Long.valueOf(id));
        OrderVO orderVO=new OrderVO();
        BeanUtils.copyProperties(order,orderVO);
        return Result.success(orderVO);
    }

    /**
     * 列表
     */
    @ApiOperation(value = "根据id集合查询")
    @GetMapping("/list")
    public Result<Object> list() {
        List<Order> orderList = orderService.list();
        List<OrderVO> orderVOList = new ArrayList<>();
        orderList.forEach(item->{
            OrderVO orderVO=new OrderVO();
            BeanUtils.copyProperties(item,orderVO);
            orderVOList.add(orderVO);

        });
        return Result.success(orderVOList);
//                return R.data(NoticeWrapper.build().pageVO(pages));
    }

    /**
     * 分页
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<Object> page(@RequestParam(defaultValue = "1") Integer current,
                       @RequestParam(defaultValue = "10") Integer size) {
        Page<Order> p=new Page<>();
        p.setCurrent(current);
        p.setSize(size);
        Page<Order> orderPage = orderService.page(p);
        Page<OrderVO> orderVoPage=new Page<>();
        BeanUtils.copyProperties(orderPage,orderVoPage);

        List<OrderVO> orderVOList = orderPage.getRecords().stream().map(item -> {
            OrderVO orderVO=new OrderVO();
            BeanUtils.copyProperties(item,orderVO);
            return orderVO;
        }).collect(Collectors.toList());

        orderVoPage.setRecords(orderVOList);
        return Result.success(orderVoPage);
//                return R.data(NoticeWrapper.build().pageVO(pages));
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperation(value = "新增或修改", notes = "传入orderQuery")
    public Result<Object> submit(@Validated(OrderQuery.Save.class) @RequestBody OrderQuery userQuery) {
        Order user=new Order();
        BeanUtils.copyProperties(userQuery,user);
        return Result.success(orderService.saveOrUpdate(user));
    }

    /**
     * 根据名字更新
     */
   /* @PostMapping("/updateByName")
    @ApiOperation(value = "根据名字更新用户", notes = "传入userQuery")
    public Result<Object> updateByName(@Validated(OrderQuery.UpdateByName.class) @RequestBody OrderQuery userQuery) {
        Order user=new Order();
        BeanUtils.copyProperties(userQuery,user);
        return Result.success(orderService.updateByName(user));
    }*/

}
