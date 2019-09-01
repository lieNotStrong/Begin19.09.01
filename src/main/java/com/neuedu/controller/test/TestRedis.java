package com.neuedu.controller.test;


import com.neuedu.common.RedisPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

@RestController
public class TestRedis {


    @Autowired
    RedisPool redisPool;

    @RequestMapping(value = "/redis")
    public String javaForRedis(){
        Jedis jedis = redisPool.getJedis();
        String success = jedis.set("cuiqiran", "sanyaTomohe");
        return success;
    }



}
