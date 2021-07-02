package cn.lngex.course.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //定义邮件的队列名字
    public static final String QUEUE_NAME_EMAIL = "queue_name_email";
    //定义短信的队列名字
    public static final String QUEUE_NAME_SMS = "queue_name_SMS";
    //站内信队列
    public static final String QUEUE_NAME_SYSTEM_MESSAGE = "queue_name_system_message";
    //定义交换机
    public static final String EXCHANGE_NAME_TOPIC = "exchange_name_topic";


    //定义交换机
    @Bean(EXCHANGE_NAME_TOPIC)
    public Exchange exchangeNameTopic(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME_TOPIC).durable(true).build();
    }

    //定义邮件的队列Bean
    @Bean(QUEUE_NAME_EMAIL)
    public Queue emailQueue(){
        return new Queue(QUEUE_NAME_EMAIL,true);
    }


    //定义短信的队列Bean
    @Bean(QUEUE_NAME_SMS)
    public Queue smsQueue(){
        return new Queue(QUEUE_NAME_SMS,true);
    }
    //定义系统消息的队列Bean
    @Bean(QUEUE_NAME_SYSTEM_MESSAGE)
    public Queue systemMessageQueue(){
        return new Queue(QUEUE_NAME_SYSTEM_MESSAGE,true);
    }

    //定义邮件的队列绑定到交换机
    @Bean
    public Binding smsQueueBinding(@Qualifier(QUEUE_NAME_SMS) Queue smsQueue,
                                   Exchange exchangeNameTopic){

        // #.sms.course
        return BindingBuilder.bind(smsQueue).to(exchangeNameTopic).with("message.sms").noargs();

    }
    //定义邮件的队列绑定到交换机
    @Bean
    public Binding emailQueueBinding(@Qualifier(QUEUE_NAME_EMAIL) Queue emailQueue,
                                   Exchange exchangeNameTopic){

        // #.email.course
        return BindingBuilder.bind(emailQueue).to(exchangeNameTopic).with("message.email").noargs();

    }
    @Bean
    public Binding systemMessageQueueBinding(
            @Qualifier(QUEUE_NAME_SYSTEM_MESSAGE) Queue systemMessageQueue,
                                   Exchange exchangeNameTopic){
        // #.systemmessage.course
        return BindingBuilder.bind(systemMessageQueue).to(exchangeNameTopic).with("message.system").noargs();

    }
}
