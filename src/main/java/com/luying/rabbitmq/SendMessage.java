package com.luying.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class SendMessage {
	private static final String QUEUE_NAME="test_queue";
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection connection = RabbitMqUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);//消费者反馈后才发下一条
		for (int i = 0; i < 50; i++) {
			String mes="hello lijan"+i;
			channel.basicPublish("", QUEUE_NAME, null, mes.getBytes());
			Thread.sleep(1000);
		}
		System.out.println("消息已发送");
		channel.close();
		connection.close();
	}
}
