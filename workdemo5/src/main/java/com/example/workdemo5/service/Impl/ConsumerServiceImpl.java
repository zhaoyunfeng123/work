package com.example.workdemo5.service.Impl;

import com.example.workdemo5.entity.MailEntity;
import com.example.workdemo5.rabbitmq.MailUtil;
import com.example.workdemo5.rabbitmq.RabbitConfig;
import com.example.workdemo5.service.CartService;
import com.example.workdemo5.service.ConsumerService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService{

    @Autowired
    MailUtil mailUtil;
    @Autowired
    CartService cartService;

    /**
     * 消费者接收消费邮件
     */
    @RabbitListener(queues = RabbitConfig.MAIT_QUEUE_NAME)
    public void realSendMail(Message message, Channel channel, MailEntity mailEntity) throws Exception {
        try{
            boolean result = mailUtil.send(mailEntity);
            if (result){
                //消息确认
                //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }else {
                ////ack返回false，并重新回到队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
                //拒绝消息
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 消费者接收消费加入购物车，购物车过期策略
     */
    //处理超时信息
    @RabbitListener(queues = RabbitConfig.BOOK_DEAD_LETTER_QUEUE_NAME)
    public void bookCartDeadLetterConsumer(Message message, Channel channel, int cartId) throws Exception {
        //根据cartID查询是否还存在购物车
        boolean exist = cartService.selectBookCartByCartId(cartId);
            if (exist){
            //此商品存在购物车，超过30分钟，删除
            boolean result = cartService.deleteBookFromCartByCartId(cartId);
            try {
                if (result){
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                }else {
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
