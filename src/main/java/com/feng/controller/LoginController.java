package com.feng.controller;

import com.feng.entity.User;
import com.feng.service.LoginService;
import com.feng.service.LogoutService;
import com.feng.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: springboot2
 * @package: com.feng.controller
 * @className: LoginController
 * @author: Ladidol
 * @description:
 * @date: 2022/4/29 13:23
 * @version: 1.0
 */

@RestController
@Slf4j
public class LoginController {


    @Autowired
    private LoginService loginService;

    @Autowired
    private LogoutService logoutService;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        log.info("访问了登录接口start");
        log.info("前端接收的User:"+ user);


        //登录
        Result login = loginService.login(user);
        log.info("访问了登录接口end");

        return login;
    }
    @GetMapping("/user/logout")
    public Result logout(){
        log.info("访问了注销接口start");

        Result result = logoutService.logout();

        log.info("访问了注销接口end");
        return result;
    }
}
