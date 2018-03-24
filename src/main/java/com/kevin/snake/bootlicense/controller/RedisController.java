package com.kevin.snake.bootlicense.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "redis")
public class RedisController {

    //redisTemplate有许多的坑,用前三思
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "script")
    public String excuteScript(){
        return "";
    }


}
