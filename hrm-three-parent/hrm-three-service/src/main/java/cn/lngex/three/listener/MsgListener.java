package cn.lngex.three.listener;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MsgListener {

    @RabbitListener(queues = {"queue_name_system_message"})
    public void getMesssage(String msg, Message message, Channel channel) throws IOException, InterruptedException {
        System.out.println(msg);
        Thread.sleep(10000);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
    }
}
