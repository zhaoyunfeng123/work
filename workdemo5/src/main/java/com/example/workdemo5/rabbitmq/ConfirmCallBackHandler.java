package com.example.workdemo5.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 实现ConfirmCallBack接口，消息发送到交换器Exchange后触发回调。
 * */
public class ConfirmCallBackHandler implements RabbitTemplate.ConfirmCallback {

    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("消息唯一标识：" + correlationData);
        System.out.println("确认结果：" + ack);
        System.out.println("失败原因：" + cause);
    }
}
