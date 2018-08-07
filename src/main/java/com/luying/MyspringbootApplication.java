package com.luying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MyspringbootApplication {//test

    public static void main(String[] args) {
        SpringApplication.run(MyspringbootApplication.class, args);
    }
}
