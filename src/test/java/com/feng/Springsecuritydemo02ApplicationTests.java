package com.feng;

import com.feng.entity.User;
import com.feng.mapper.UserMapper;
import com.feng.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Springsecuritydemo02ApplicationTests {

    @Resource
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    void test1(){
        //由于盐不一样
        //$2a$10$Cdh0RscsnDXWLgP.dcM2z.8GbAcQH/slAO.hbww4QYVbXx/Dw2CZu
        //$2a$10$Acy.TQCwZ5tBsJuuPnUW6uR8TNwIu/AJeXw.3mA42jYQN3sh1IPzi
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        String encode2 = passwordEncoder.encode("ladidol");
        String encode3 = passwordEncoder.encode("ladidol");
        System.out.println(encode);
        System.out.println(encode2);
        System.out.println(encode3);

        //可能密文前缀可以反解密, 所以可以解密匹配

        System.out.println(passwordEncoder
                .matches("ladidol",
                        "{bcrypt}$2a$10$Acy.TQCwZ5tBsJuuPnUW6uR8TNwIu/AJeXw.3mA42jYQN3sh1IPzi"));
    }

    @Test
    void test2() {
        Map claims = new HashMap();
        claims.put("name","ladidol");
        String jwt = JwtUtils.createJWT(claims,10000000L);
        System.out.println(jwt);
        String object = String.valueOf(JwtUtils.verifyJwt(jwt));
        System.out.println(object);
    }




}
