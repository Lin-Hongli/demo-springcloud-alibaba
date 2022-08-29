package com.lhl.util.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhl.util.result.Result;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Component
@Slf4j
public class PageAspect {
    @SneakyThrows
    @Around("@annotation(startPage)")
    public Object around(ProceedingJoinPoint joinPoint,StartPage startPage){
        // 1.获取pageNo和pageSize
        ServletRequestAttributes currentRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = currentRequestAttributes.getRequest();

        //JSON格式？
        /*String body = IOUtils.read(request.getReader());
        JSONObject jsonObject = JSONObject.parseObject(body);
        String pageNo = jsonObject.getString(startPage.pageNo());
        String pageSize = jsonObject.getString(startPage.pageSize());*/

        int pageNo = 1;
        int pageSize = 10;
        if(!ObjectUtils.isEmpty(request.getParameter(startPage.pageNo()))){
            pageNo =Integer.valueOf(request.getParameter(startPage.pageNo()));
        }
        if(!ObjectUtils.isEmpty(request.getParameter(startPage.pageSize()))){
            pageSize =Integer.valueOf(request.getParameter(startPage.pageSize()));
        }

        //2.开始分页
        PageHelper.startPage(pageNo, pageSize);

        //3. 调用原本方法的内容并获取返回值
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed(args);//调用方法

        //4.根据自己的接口返回类型对数据进行再封装
        PageInfo pageInfo = null;
        Result respResult = null;

        /**
         * {@link com.github.pagehelper.Page}
         * 如果方法返回Page则直接使用，进行强制转换就能获取到 total、pageNo、pageSize
         */
        if (result instanceof Page) {
            Page page = (Page) result;
            pageInfo = new PageInfo(page);
        }
        //如果是自定义Result则getData再封装
        else if (result instanceof Result) {
            respResult = (Result) result;
            //列表数据
            Object data = respResult.getData();
            if (data instanceof List) {
                pageInfo = new PageInfo((List) data);
            }
        }
        //封装成自己的pegeInfo
        if (pageInfo != null) {
            com.lhl.util.page.PageInfo myPageInfo=new com.lhl.util.page.PageInfo();
            myPageInfo.setPageNo(pageInfo.getPageNum());
            myPageInfo.setPageSize(pageInfo.getPageSize());
            myPageInfo.setTotal(pageInfo.getTotal());
            myPageInfo.setRows(pageInfo.getList());

            respResult.setData(myPageInfo);
            return respResult;
        }
        //查不到数据或无法处理，原路返回
        return result;
    }
}
