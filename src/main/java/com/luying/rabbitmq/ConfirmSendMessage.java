package com.luying.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ConfirmSendMessage {
	private static final String QUEUE_NAME = "test_queue_confirm";

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection connection = RabbitMqUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.confirmSelect();
		channel.basicQos(1);// 消费者反馈后才发下一条

		String mes = "hello lijan";
		for (int i = 0; i < 20; i++) {
			
			channel.basicPublish("", QUEUE_NAME, null, mes.getBytes());
		}
		if (!channel.waitForConfirms()) {
			System.out.println("发送失败");
		}else {
			
			System.out.println("消息已发送ok");
		}
		channel.close();
		connection.close();
	}
}
