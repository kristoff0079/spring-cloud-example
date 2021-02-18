package org.springcloud.web1.controller;

import org.springcloud.common.redis.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping("/")
public class Web1Controller {
    @Autowired
    private RedisLock redisLock;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping(path = "/hello")
    public String hello(@RequestParam("name") String name, @Context HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        System.out.println("URL:" + url);
        return "Hello, " + url;
    }

    @GetMapping("/lock")
    public String lock(@RequestParam("name") String name, @Context HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        System.out.println("url====>" + url);
        String token = redisLock.tryLock(name, 10000);

        if (token == null) {
            return "Repeat request.";
        }

        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() <= start + 5000) {

        }

        boolean unlocked = redisLock.unlock(name, token);
        System.out.println("unlocked====>" + unlocked);

        return "Request success:" + url;
    }

    @GetMapping("/addRedisValue")
    public String addRedisValue(@RequestParam("name") String name) {
        redisTemplate.opsForValue().set(name, name);
        return "Success";
    }
}
