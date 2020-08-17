package com.example.workdemo5.service.Impl;

import com.example.workdemo5.entity.MailEntity;
import com.example.workdemo5.rabbitmq.RabbitConfig;
import com.example.workdemo5.service.SendService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SendServiceImpl implements SendService {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RabbitConfig rabbitConfig;

    /**
     * 发送邮件
     */
    public void sendMailToQueue(MailEntity mailEntity) {
        //创建唯一标识UUID
        String uId = UUID.randomUUID().toString().replace("-", "");
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(uId);
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE_NAME, RabbitConfig.MAIL_ROUTING_KEY_NAME, mailEntity, correlationData);
    }
    /**
     * book添加到购物车，生产者发送消息
     */
    public void sendCartIdQueue(int cartId) {
        String uId = UUID.randomUUID().toString().replace("-","");
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(uId);
        rabbitTemplate.convertAndSend(RabbitConfig.BOOK_CART_EXCHANGE_NAME,RabbitConfig.BOOK_CART_ROUTING_KEY_NAME,cartId, correlationData);
    }
}
