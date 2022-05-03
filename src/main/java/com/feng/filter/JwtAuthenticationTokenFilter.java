package com.feng.filter;

import com.feng.entity.LoginUser;
import com.feng.entity.User;
import com.feng.utils.JwtUtils;
import com.feng.utils.RedisService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @projectName: springboot2
 * @package: com.feng.filter
 * @className: JwtAuthenticationTokenFilter
 * @author: Ladidol
 * @description:
 * @date: 2022/4/30 1:18
 * @version: 1.0
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        log.info("从前端拿到的token: "+token);
        if (token == null) {
            filterChain.doFilter(request, response);
            return ;
        }

        // 解析token
        Claims claims = JwtUtils.verifyJwt(token);
        String id = String.valueOf(claims.get("id"));
        String redisKey = "login" + id;
        log.info("解析得到的redisKey: "+redisKey);

        // 从redis中读取信息
        LoginUser loginUser = (LoginUser) redisService.get(redisKey);
        log.info("解析到的用户: "+loginUser);
        if (loginUser == null) {
            throw new RuntimeException("用户未登录");
        }

        // 存入SecurityContextHolder, 其他filter会通过这个来获取当前用户信息
        // 获取权限信息封装到authentication中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        // 放行
        filterChain.doFilter(request, response);
    }
}