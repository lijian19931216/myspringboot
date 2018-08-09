package com.luying.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: lijian
 * @create: 2018-08-08
 **/
@Service
public class ScheduleServiceImpl {
    @Scheduled(cron = "*/2 * * * * ?")//每2秒
    public void hello(){
        System.out.println("nihao");
    }
}
