package com.feng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.feng.mapper")
public class Springsecuritydemo02Application {

    public static void main(String[] args) {
        SpringApplication.run(Springsecuritydemo02Application.class, args);
    }

}
