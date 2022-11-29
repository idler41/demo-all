package com.demo.jackson.controller;

import com.demo.jackson.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-11-29 15:49:46
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public User hello() {
        User user = new User();
        user.setId(1);
        user.setCreateTime(LocalDateTime.now());
        return user;
    }

    @GetMapping("/set1")
    public User set1(User query) {
        // Failed to convert property value of type 'java.lang.String' to required type 'java.time.LocalDateTime' for property 'createTime'
        return query;
    }

    @PostMapping("/set")
    public User set(@RequestBody User query) {
        // 正常转换
        return query;
    }
}
