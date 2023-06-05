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
package com.xxxcloud.common.core.utils;

import cn.hutool.core.convert.Convert;
import com.xxxcloud.common.core.constants.ClaimsKeyConstant;
import com.xxxcloud.common.core.constants.JwtTokenConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * JwtUtil
 *
 * @author Chill
 */
public class JwtUtil {
    public static String BASE64_SECRET = Base64.getEncoder().encodeToString(JwtTokenConstant.SECRET.getBytes(StandardCharsets.UTF_8));


    /**
     * 截取请求头的token串
     *
     * @param auth token
     * @return String
     */
    public static String getToken(String auth) {
        // 裁剪掉前缀
        if (!StringUtils.isEmpty(auth) && auth.startsWith(JwtTokenConstant.PREFIX)) {
            return auth.replaceFirst(JwtTokenConstant.PREFIX, "");
        }
        return null;
    }

    /**
     * 解析token，从中获取数据
     *
     * @param token
     * @return Claims
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode(JwtUtil.BASE64_SECRET))
                    .parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取荷载中UserKey的值
     *
     * @param claims 身份信息
     * @return 用户信息标识
     */
    public static String getUserKeyByClaims(Claims claims) {
        return getValue(claims, ClaimsKeyConstant.USER_KEY);
    }

	/**
	 * 获取荷载中UserId的值
	 *
	 * @param claims 身份信息
	 * @return 用户ID
	 */
	public static String getUserIdByClaims(Claims claims) {
		return getValue(claims, ClaimsKeyConstant.USER_ID);
	}

	/**
	 * 获取荷载中UserName的值
	 *
	 * @param claims 身份信息
	 * @return 用户名
	 */
	public static String getUserNameByClaims(Claims claims) {
		return getValue(claims, ClaimsKeyConstant.USER_NAME);
	}

    /**
     * 根据荷载键获取值
     *
     * @param claims 荷载(身份信息)
     * @param key    键
     * @return 值
     */
    public static String getValue(Claims claims, String key) {
        return Convert.toStr(claims.get(key), "");
    }
}
