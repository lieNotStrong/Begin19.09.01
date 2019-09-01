package com.neuedu.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者-投递消息
 * */
public class Producer4DirectExchange {


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
//            String routingKey = "routerkey";
            String routingKey = "Declare_Exchange_RoutingKey";
            String exchangeName = "Declare_Exchange";
            for (int i = 0;i<5;i++){
                String msg = "Hello,Declare_RabbitMq"+i;
                channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
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
