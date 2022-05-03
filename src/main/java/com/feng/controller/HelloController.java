package com.feng.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: springboot2
 * @package: com.feng.controller
 * @className: HelloController
 * @author: Ladidol
 * @description:
 * @date: 2022/4/28 21:30
 * @version: 1.0
 */
@RestController
@RequestMapping
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello SpringSecurity";
    }


    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/helloAdmin")
    public String hello2(){
        return "hello admin";
    }


    @PreAuthorize("hasAuthority('noway')")
    @GetMapping("/noway")
    public String hello3(){
        return "你好测试失败了";
    }




}
