package com.huazai.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 华仔
 * @date 2018/7/3 15:52
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("")
    public String sayHello() {
        return "你好";
    }
}
