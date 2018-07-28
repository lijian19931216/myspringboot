package com.luying.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author 李剑
 * @Description:
 * @date 2018年6月17日  
 */
public class SendMessage3 {
	private static final String EXCHANGE_NAME = "test_exchange_direct";

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection connection = RabbitMqUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME,"direct");
		String mes = "hello lijan";
		String routingKey="error";
		channel.basicPublish(EXCHANGE_NAME,routingKey, null, mes.getBytes());

		System.out.println("消息已发送");
		channel.close();
		connection.close();
	}
}
