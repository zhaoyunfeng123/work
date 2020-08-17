package com.example.workdemo5.service;

import com.example.workdemo5.entity.MailEntity;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
public interface ConsumerService {
    /**
     * 消费者接收消费邮件
     */
    void realSendMail(Message message, Channel channel, MailEntity mailEntity) throws Exception;
    /**
     * 消费者接收消费加入购物车
     */
    //处理超时信息
    void bookCartDeadLetterConsumer(Message message, Channel channel, int cartId) throws Exception;
}
