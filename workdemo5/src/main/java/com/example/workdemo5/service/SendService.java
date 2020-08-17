package com.example.workdemo5.service;

import com.example.workdemo5.entity.MailEntity;
import org.springframework.stereotype.Component;

@Component
public interface SendService {

    /**
     * 生产者发送邮件
     */
    void sendMailToQueue(MailEntity mailEntity);

    /**
     * book添加到购物车，生产者发送消息
     */
    void sendCartIdQueue(int cartId);
}
