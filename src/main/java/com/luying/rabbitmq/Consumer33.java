package com.luying.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Consumer33 {
	private static final String QUEUE_NAME="test_queue_message";
	private static final String EXCHANGE_NAME = "test_exchange_queue";

	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		
		Connection connection = RabbitMqUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
		channel.basicQos(1);//消费者反馈后才发下一条
		com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
			 public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope, com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws IOException {
				 String mess=new String(body,"utf-8");
				 System.out.println(mess+"[33]");
				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					System.out.println("*33*");
					//手动回执
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			 }
		 };
		 //true 自动应答
		 channel.basicConsume(QUEUE_NAME, false,consumer);
	}
}
