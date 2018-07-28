package com.luying.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class Producer {
	public static void main(String[] args) throws MQClientException, InterruptedException {
	DefaultMQProducer producer = new DefaultMQProducer("quickstart_producer");
	//设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
    //NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
    producer.setNamesrvAddr("192.168.186.129:9876;192.168.186.128:9876");


	producer.start();
	for (int i = 0; i <=10; i++) {
		try {
            Message msg = new Message("TopicQuickStart",// topic
                    "TagA",// tag
                    ("Hello RocketMQ " + i).getBytes()// body
            );
            
            //调用producer的send()方法发送消息
            //这里调用的是同步的方式，所以会有返回结果
            SendResult sendResult = producer.send(msg);
            
            //打印返回结果，可以看到消息发送的状态以及一些相关信息
            System.out.println(sendResult);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.sleep(1000);
        }
	}
	//发送完消息之后，调用shutdown()方法关闭producer
    producer.shutdown();
	}
}
