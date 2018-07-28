package com.luying.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class ConfirmConsumer {
	private static final String QUEUE_NAME="test_queue_confirm";
	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		/*Connection connection = RabbitMqUtil.getConnection();
		Channel channel = connection.createChannel();
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true,consumer);
		while(true) {
		Delivery delivery = consumer.nextDelivery();	
		String getMessage=new String(delivery.getBody());
		System.out.println(getMessage);*/
		Connection connection = RabbitMqUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);//消费者反馈后才发下一条
		com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
			 public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope, com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws IOException {
				 String mess=new String(body,"utf-8");
				 System.out.println(mess+"[1]");
				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					System.out.println("*1*");
					//手动回执
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			 }
		 };
		 //true 自动应答
		 channel.basicConsume(QUEUE_NAME, false,consumer);
	}
}
