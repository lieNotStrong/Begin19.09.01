package com.neuedu.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Cosumer {


    public static void main(String[] args) {


        //step1:创建connctionFactory；
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("39.96.161.245");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("Lie1998..");
        //step2:根据ConnectionFactory创建Connection
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            String quequeName = "routerkey";
            //创建一个队列
            channel.queueDeclare(quequeName,true,false,false,null);
            //声明一个消费者
            QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
            //绑定channel与队列
            channel.basicConsume(quequeName,true,queueingConsumer);
            //while循环中取，这是一个死循环，只要生产者那边有消息进入，消费者这边就会取到消息;
            while (true){
                //消费者取消息，把消息封装到delivery中
                QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
                //通过getbody()方法取消息
                String result = new String(delivery.getBody());
                System.out.println("Consumer=="+result);
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {

                channel.close();
                connection.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }
    }
}
