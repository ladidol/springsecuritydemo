package com.feng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.feng.entity.LoginUser;
import com.feng.entity.User;
import com.feng.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @projectName: springboot2
 * @package: com.feng.service.impl
 * @className: UserDetailsServiceImpl
 * @author: Ladidol
 * @description: 从数据库中, 通过username查询用户信息
 * @date: 2022/4/28 22:19
 * @version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        System.out.println("username是"+username);
        wrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            // 异常信息会被ExceptionTranslationFilter捕获到
            throw new RuntimeException("用户不存在或密码错误");
        }
        // 授权
        // todo 这里可以用权限集来做
        List<String> permissions = new ArrayList<>(Arrays.asList("test"));


        System.out.println("loginUser是:"+new LoginUser(user,permissions));
        // 数据封装成UserDetails返回
        return new LoginUser(user,permissions);
    }
}