package com.luying.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: lijian
 * @create: 2018-08-08
 **/
@Service
public class TestServiceImpl {
    @Async
    public void hello(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("nihaoaaaa");
    }
}
