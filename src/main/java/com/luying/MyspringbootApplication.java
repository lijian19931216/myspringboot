package com.luying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync //开启异步
@SpringBootApplication
@EnableCaching
//@EnableRabbit
@EnableScheduling
public class MyspringbootApplication {//test

    public static void main(String[] args) {
        SpringApplication.run(MyspringbootApplication.class, args);
    }
}
