package com.xxxcloud.service.order.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxcloud.common.core.emuns.BizzErrorCodeEnum;
import com.xxxcloud.common.core.exception.BizzException;
import com.xxxcloud.common.core.result.R;
import com.xxxcloud.service.order.model.vo.OrderVO;
import com.xxxcloud.service.order.model.entity.Order;
import com.xxxcloud.service.order.model.query.OrderQuery;
import com.xxxcloud.service.order.service.IOrderService;
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
    public R<Object> payOrder(Integer num) {
        return orderService.payOrder(num);
    }


    /**
     * 新增
     *
     * 有通用异常处理，参数列表不需要跟BindingResult bindingResult
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增", notes = "传入orderQuery")
    public R<Object> save(@Validated({OrderQuery.Save.class}) @RequestBody OrderQuery orderQuery) {
        Order order=new Order();
        BeanUtils.copyProperties(orderQuery,order);
        return R.ok(orderService.save(order));
    }

    /**
     * 删除
     */
    @PostMapping(value = "/remove")
    @ApiOperation(value = "删除", notes = "传入orderQuery")
    public R<Object> remove(@RequestParam(name = "id") @NotEmpty(message = "id不能为空") String id) {
//        Func.toLongList(ids)
        return R.ok(orderService.removeById(Long.valueOf(id)));
    }

    /**
     * 修改
     */
    @PostMapping("/modify")
    @ApiOperation(value = "修改", notes = "传入orderQuery")
    public R<Object> modify(@Validated(OrderQuery.Update.class) @RequestBody OrderQuery orderQuery) {
        Order order=new Order();
        BeanUtils.copyProperties(orderQuery,order);
        order.setId(Long.valueOf(orderQuery.getId()));

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Order::getId,order.getId());
        boolean update = orderService.update(order, queryWrapper);
        if(!update){
            throw new BizzException(BizzErrorCodeEnum.USER10010003,order.getId());
        }
        return R.ok();
    }

    /**
     * 详情
     *
     * URL 路径不能使用大写 单词如果需要分隔，统一使用下划线。
     */
    @ApiOperation(value = "根据id查询(接口说明)", httpMethod = "GET(接口请求方式)", response = R.class, notes = "1.0.0(接口发布说明)")
    @ApiParam(required = true, name = "id(参数名称)", value = "这是一个id(参数具体描述)")
    @GetMapping(path = "/query")
    public R<Object> query(@RequestParam(name = "id") @NotEmpty(message = "id不能为空") String id) {
        Order order = orderService.getById(Long.valueOf(id));
        OrderVO orderVO=new OrderVO();
        BeanUtils.copyProperties(order,orderVO);
        return R.ok(orderVO);
    }

    /**
     * 列表
     */
    @ApiOperation(value = "根据id集合查询")
    @GetMapping("/list")
    public R<Object> list() {
        List<Order> orderList = orderService.list();
        List<OrderVO> orderVOList = new ArrayList<>();
        orderList.forEach(item->{
            OrderVO orderVO=new OrderVO();
            BeanUtils.copyProperties(item,orderVO);
            orderVOList.add(orderVO);

        });
        return R.ok(orderVOList);
//                return R.data(NoticeWrapper.build().pageVO(pages));
    }

    /**
     * 分页
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R<Object> page(@RequestParam(defaultValue = "1") Integer current,
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
        return R.ok(orderVoPage);
//                return R.data(NoticeWrapper.build().pageVO(pages));
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperation(value = "新增或修改", notes = "传入orderQuery")
    public R<Object> submit(@Validated(OrderQuery.Save.class) @RequestBody OrderQuery userQuery) {
        Order user=new Order();
        BeanUtils.copyProperties(userQuery,user);
        return R.ok(orderService.saveOrUpdate(user));
    }

    /**
     * 根据名字更新
     */
   /* @PostMapping("/updateByName")
    @ApiOperation(value = "根据名字更新用户", notes = "传入userQuery")
    public R<Object> updateByName(@Validated(OrderQuery.UpdateByName.class) @RequestBody OrderQuery userQuery) {
        Order user=new Order();
        BeanUtils.copyProperties(userQuery,user);
        return R.ok(orderService.updateByName(user));
    }*/

}
