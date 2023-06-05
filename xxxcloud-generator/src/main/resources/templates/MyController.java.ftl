package ${package.Controller};


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${cfg.Organization}.${cfg.ModuleName}.core.BusinessErrorCodeEnum;
import ${cfg.Organization}.${cfg.ModuleName}.core.BusinessException;
import ${cfg.Organization}.${cfg.ModuleName}.core.R;
import ${package.Entity}.${entity};
import ${cfg.PackageQuery}.${cfg.EntityQuery};
import ${cfg.PackageVO}.${cfg.EntityVO};
import ${package.Service}.${table.serviceName};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
<#if restControllerStyle>
<#else>
import org.springframework.stereotype.Controller;
</#if>

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!}Controller
 * </p>
 *
 * @author ${author}
 * @since ${.now}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
@Api(tags = "${table.comment!}Api")
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
@Api(tags = "${table.comment!}Api")
public class ${table.controllerName} {
</#if>
    @Resource
    private I${entity}Service ${table.entityPath}Service;

    /**
    * 新增
    */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增", notes = "传入${entity}Query")
    public R<Object> save(@Validated @RequestBody ${entity}Query ${table.entityPath}Query) {
        ${entity} ${table.entityPath} = new ${entity}();
        BeanUtils.copyProperties(${table.entityPath}Query, ${table.entityPath});
        return R.ok(${table.entityPath}Service.save(${table.entityPath}));
    }

    /**
    * 删除
    */
    @PostMapping(value = "/remove")
    @ApiOperation(value = "删除", notes = "传入id")
        public R<Object> remove(@RequestParam(name = "id") @NotEmpty(message = "id不能为空") String id) {
        return R.ok(${table.entityPath}Service.removeById(Long.valueOf(id)));
    }

    /**
    * 修改
    */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "传入${entity}Query")
    public R<Object> update(@Validated(${entity}Query.Update.class) @RequestBody ${entity}Query ${table.entityPath}Query) {
        ${entity} ${table.entityPath} = new ${entity}();
        BeanUtils.copyProperties(${table.entityPath}Query, ${table.entityPath});
        ${table.entityPath}.setId(Long.valueOf(${table.entityPath}Query.getId()));

        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(${entity}::getId, ${table.entityPath}.getId());
        boolean update = ${table.entityPath}Service.update(${table.entityPath}, queryWrapper);
        if (!update) {
            throw new BusinessException(BusinessErrorCodeEnum.USER10010003, ${table.entityPath}.getId());
        }
        return R.ok();
    }

    /**
    * 详情
    */
    @ApiOperation(value = "根据id查询(接口说明)", httpMethod = "GET", response = R.class, notes = "1.0.0版本")
    @ApiParam(required = true, name = "id(参数名称)", value = "这是一个id(参数具体描述)")
    @GetMapping(path = "/query")
    public R<Object> query(@RequestParam(name = "id") @NotEmpty(message = "id不能为空") String id) {
        ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(Long.valueOf(id));
        ${entity}VO ${table.entityPath}VO = new ${entity}VO();
        BeanUtils.copyProperties(${table.entityPath}, ${table.entityPath}VO);
        return R.ok(${table.entityPath}VO);
    }

    /**
    * 列表
    */
    @ApiOperation(value = "根据id集合查询")
    @GetMapping("/list")
    public R<Object> list() {
        List<${entity}> ${table.entityPath}List = ${table.entityPath}Service.list();
        List<${entity}VO> ${table.entityPath}VOList = new ArrayList<>();
        ${table.entityPath}List.forEach(item -> {
            ${entity}VO ${table.entityPath}VO = new ${entity}VO();
            BeanUtils.copyProperties(item, ${table.entityPath}VO);
            ${table.entityPath}VOList.add(${table.entityPath}VO);
        });
        return R.ok(${table.entityPath}VOList);
    }

    /**
    * 分页
    */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public R<Object> page(@RequestParam(defaultValue = "1") Integer current,
    @RequestParam(defaultValue = "10") Integer size) {
        Page<${entity}> p = new Page<>();
        p.setCurrent(current);
        p.setSize(size);
        Page<${entity}> ${table.entityPath}Page = ${table.entityPath}Service.page(p);
        Page<${entity}VO> ${table.entityPath}VoPage = new Page<>();
        BeanUtils.copyProperties(${table.entityPath}Page, ${table.entityPath}VoPage);

        List<${entity}VO> ${table.entityPath}VOList = ${table.entityPath}Page.getRecords().stream().map(item -> {
            ${entity}VO ${table.entityPath}VO = new ${entity}VO();
            BeanUtils.copyProperties(item, ${table.entityPath}VO);
            return ${table.entityPath}VO;
        }).collect(Collectors.toList());

        ${table.entityPath}VoPage.setRecords(${table.entityPath}VOList);
        return R.ok(${table.entityPath}VoPage);
    }

    /**
    * 新增或修改
    */
    @PostMapping("/submit")
    @ApiOperation(value = "新增或修改", notes = "传入${entity}Query")
    public R<Object> submit(@Validated @RequestBody ${entity}Query ${table.entityPath}Query) {
        ${entity} ${table.entityPath} = new ${entity}();
        BeanUtils.copyProperties(${table.entityPath}Query, ${table.entityPath});
        return R.ok(${table.entityPath}Service.saveOrUpdate(${table.entityPath}));
    }
}
</#if>
