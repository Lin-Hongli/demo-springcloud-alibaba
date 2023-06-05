package com.xxxcloud.service.order;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author LinHongli Email:2381555134@qq.com
 * @version v1.0
 * @Description
 * @date 2021/07/08 9:41
 */
public class OrderTest extends OrderApplicationTest {

    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void test() {

        redisTemplate.opsForValue().append("a","6666666");
        redisTemplate.expire("a", Duration.ofMinutes(1));
    }
}
