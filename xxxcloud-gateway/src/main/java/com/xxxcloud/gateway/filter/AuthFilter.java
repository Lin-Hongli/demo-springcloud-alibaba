/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xxxcloud.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.common.utils.StringUtils;
import com.xxxcloud.common.core.constants.ClaimsKeyConstant;
import com.xxxcloud.common.core.constants.JwtTokenConstant;
import com.xxxcloud.common.core.emuns.ErrorCodeEnum;
import com.xxxcloud.common.core.result.R;
import com.xxxcloud.common.core.utils.JwtUtil;
import com.xxxcloud.common.core.utils.ServletUtil;
import com.xxxcloud.gateway.props.WhiteListProperties;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * 鉴权认证
 *
 * @author Chill
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {
    @Resource
    private WhiteListProperties whiteListProperties;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获得请求和响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest.Builder requestBuilder = request.mutate();

        //放行白名单路径
        String path = request.getURI().getPath();
		boolean isSkip = whiteListProperties.getWhiteList().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
		if (isSkip) {
            return chain.filter(exchange);
        }

		//检查是否有令牌
        String headerToken = request.getHeaders().getFirst(JwtTokenConstant.AUTH_KEY);
        String paramToken = request.getQueryParams().getFirst(JwtTokenConstant.AUTH_KEY);
        if (StringUtils.isBlank(headerToken) && StringUtils.isBlank(paramToken)) {
            return unauthorized(response, "令牌不能为空");
        }

        //检查令牌有效性
        String auth = StringUtils.isBlank(headerToken) ? paramToken : headerToken;
        String token = JwtUtil.getToken(auth);
        Claims claims = JwtUtil.parseToken(token);
        if (claims == null) {
            return unauthorized(response, "令牌已过期或验证不正确");
        }

        //一般到这里即可放行请求，但此处配合redis可做用户强制下线
        //检查是否是登录状态
        String userKey = JwtUtil.getUserKeyByClaims(claims);
        //TODO redis
        //boolean isLogin = redisService.hasKey(RedisConstant.LOGIN_USERS + userKey);
        boolean isLogin = true;
        if (!isLogin) {
            return unauthorized(response, "登录状态已过期");
        }

        //获取用户信息
        String userId = JwtUtil.getUserIdByClaims(claims);
        String userName = JwtUtil.getUserNameByClaims(claims);
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userName)) {
            return unauthorized(response, "令牌验证失败");
        }

        // 设置用户信息到请求头
        addHeader(requestBuilder, ClaimsKeyConstant.USER_KEY, userKey);
        addHeader(requestBuilder, ClaimsKeyConstant.USER_ID, userId);
        addHeader(requestBuilder, ClaimsKeyConstant.USER_NAME, userName);
        // 内部请求来源清除
        removeHeader(requestBuilder, ClaimsKeyConstant.FROM_SOURCE);

        exchange.mutate().request(requestBuilder.build()).build();
        return chain.filter(exchange);
    }

    private void removeHeader(ServerHttpRequest.Builder requestBuilder, String headerKey) {
        requestBuilder.headers(httpHeaders -> httpHeaders.remove(headerKey));
    }

    private void addHeader(ServerHttpRequest.Builder requestBuilder, String headerKey, Object value) {
        if (value == null) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtil.urlEncode(valueStr);
        //修改或添加头
        requestBuilder.header(headerKey, valueEncode);
    }

    private Mono<Void> unauthorized(ServerHttpResponse response, String msg) {
		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        R<?> result = R.fail(ErrorCodeEnum.UNAUTHORIZED, msg);
        DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -200;
    }

}
