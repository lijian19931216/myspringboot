package com.luying.service.impl;

import com.luying.cache.Department;
import com.luying.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: lijian
 * @create: 2018-08-06
 **/
@Service
public class DeptServiceImpl {
    @Autowired
    DepartmentMapper DepartmentMapper;
    @Cacheable(value = "dept",cacheManager = "deptCacheManager")
    public Department getDeptById(Integer id){
        Department dept=DepartmentMapper.getDeptById(id);
        return dept;
    }
    /*@RabbitListener(queues = "atguigu.news")
    public void rabbitListen(String s){
        System.out.println("收到消息"+s);
    }
    @RabbitListener(queues = "atguigu.news")
    public void getMsg(Message msg){
        System.out.println(msg.getBody());
        System.out.println(msg.getClass());
    }*/

}
