package com.luying;

import com.luying.bean.User;
import com.luying.cache.Employee;
import com.luying.service.impl.EmpServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
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
    //广播

    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout", "", "广播");
    }

    @Autowired
    AmqpAdmin amqpAdmin;
    @Test
    public void creatExchange(){

       // amqpAdmin.declareExchange(new DirectExchange("my.exchange"));
//        amqpAdmin.declareQueue(new Queue("myquene"));
        amqpAdmin.declareBinding(new Binding("myquene", Binding.DestinationType.QUEUE,"my.exchange", "hhh", null));
    }
    @Autowired
    JavaMailSender sender;
    @Test
    public void testmail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("开会");
        simpleMailMessage.setText("21:55kaihui");
        simpleMailMessage.setTo("lijian@landfalcon.cn");
        simpleMailMessage.setFrom("928229131@qq.com");
        sender.send(simpleMailMessage);
    }

    @Test
    public void testmailplus() throws MessagingException {//带附件
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        mimeMessageHelper.setSubject("开会");
        mimeMessageHelper.setText("<h style='color:red'>8点开会</h>",true);
        mimeMessageHelper.setTo("lijian@landfalcon.cn");
        mimeMessageHelper.setFrom("928229131@qq.com");
        mimeMessageHelper.addAttachment("1.jpg", new File("C:\\Users\\lj\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\Content.IE5\\UV9UBZGZ\\1.jpg"));
        sender.send(mimeMessage);
    }
}
