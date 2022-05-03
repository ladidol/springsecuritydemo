package com.feng.service.impl;

import com.feng.entity.LoginUser;
import com.feng.entity.User;
import com.feng.service.LoginService;
import com.feng.utils.JwtUtils;
import com.feng.utils.RedisService;
import com.feng.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @projectName: springboot2
 * @package: com.feng.service.impl
 * @className: LoginServiceImpl
 * @author: Ladidol
 * @description:
 * @date: 2022/4/29 13:26
 * @version: 1.0
 */


@Service
@Slf4j
public class LoginServiceImpl implements LoginService {


    //从容器中注入这个
    @Autowired
    AuthenticationManager authenticationManager;

    @Resource
    RedisService redisService;

    @Override
    public Result login(User user) {

        //先得到前端传入的账号密码Authentication对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());


        //AuthenticationManager authentication进行用户认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        System.out.println("authencation: "+ authentication);


        //如果没有认证成功, 给出相应的提示
        if(Objects.isNull(authentication)){
            throw new RuntimeException("登陆失败");
        }

        //认证成功生成jwt, jwt存入Result中, 返回
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        System.out.println("看一下在LoginService中的loginUser: "+ loginUser);
        String id = loginUser.getUser().getId().toString();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        String jwt = JwtUtils.createJWT(claims,10000000L);
        //userid作为key
        //把LoginUser对象存入redis

//        User userMap = loginUser.getUser();
        log.info("token: "+jwt);

        redisService.set("login"+loginUser.getUser().getId(),loginUser);

        return new Result<>(jwt,true,"登录成功");

    }
}
