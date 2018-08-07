package com.luying;

import com.luying.bean.User;
import com.luying.cache.Employee;
import com.luying.service.impl.EmpServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyspringbootApplicationTests {
    @Autowired
    User user;

    @Test
    public void contextLoads() {
        System.out.println(user);
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    EmpServiceImpl empService;

    @Autowired
    RedisTemplate<Object, Employee> myRedisTemplate;

    @Test
    public void testRedis() {
        //stringRedisTemplate.opsForValue().append("name", "lijian");
      //  redisTemplate.opsForValue().set("emp", empService.getEmp(1));
        myRedisTemplate.opsForValue().set("emp1", empService.getEmp(2));


    }
    @Autowired
    RabbitTemplate rabbitTemplate;
    //点对点
    @Test
    public void direct(){
        Map<String,Object> map=new HashMap<>();
        map.put("msg", "nihao");
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", map);
    }

    @Test
    public void receiveMsg() {
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o);
    }
}
