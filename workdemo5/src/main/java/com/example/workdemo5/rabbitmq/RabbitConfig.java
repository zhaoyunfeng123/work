package com.example.workdemo5.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    /**发送邮件的队列、交换机、路由键*/
    public static final String MAIT_QUEUE_NAME = "mail.queue.two";
    public static final String MAIL_EXCHANGE_NAME = "mail.exchange.two";
    public static final String MAIL_ROUTING_KEY_NAME = "mail.routing.key.two";

    /**购物车过期策略：*/
    //正常队列、交换机、路由键
    public static final String BOOK_CART_QUEUE_NAME = "book.cart.queue";
    public static final String BOOK_CART_EXCHANGE_NAME = "book.cart.exchange";
    public static final String BOOK_CART_ROUTING_KEY_NAME = "book.cart.routing.key";
    //死信队列、交换机、路由键
    public static final String BOOK_DEAD_LETTER_QUEUE_NAME = "book.dead.letter.queue";
    public static final String BOOK_DEAD_LETTER_EXCHANGE_NAME = "book.dead.letter.exchange";
    public static final String BOOK_DEAD_LETTER_ROUTING_KEY_NAME = "book.dead.letter.routing.key";

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //消息发送确认，发送到交换器Exchange后触发回调
        rabbitTemplate.setConfirmCallback(new ConfirmCallBackHandler());
        //消息发送确认，如果消息从交换器发送到对应队列失败时触发
        //触发setReturnCallback回调必须设置mandatory=true，否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new ReturnCallBackHandler());
        return  rabbitTemplate;
    }
    /**book添加到购物车，正常队列*/
    @Bean
    Queue bookQueue(){
        Map<String, Object> map = new HashMap<String, Object>();
        //设置生存时间（30分钟）购物车过期时间30分钟
        map.put("x-message-ttl",30*60*1000);
//        map.put("x-message-ttl",20*1000);//
        //绑定死信交换机
        map.put("x-dead-letter-exchange",BOOK_DEAD_LETTER_EXCHANGE_NAME);
        //绑定死信路由键
        map.put("x-dead-letter-routing-key",BOOK_DEAD_LETTER_ROUTING_KEY_NAME);
        return new Queue(BOOK_CART_QUEUE_NAME,true,false,false,map);
    }
    /**book添加到购物车，正常交换机*/
    @Bean
    DirectExchange bookDirectExchange(){
        return new DirectExchange(BOOK_CART_EXCHANGE_NAME,true,false);
    }
    /**book添加到购物车，正常队列与交换机的绑定*/
    @Bean
    Binding bookBinding(){
        return BindingBuilder.bind(bookQueue()).to(bookDirectExchange()).with(BOOK_CART_ROUTING_KEY_NAME);
    }
    /**book添加到购物车，死信队列*/
    @Bean
    Queue bookDeadQueue(){
        return new Queue(BOOK_DEAD_LETTER_QUEUE_NAME,true);
    }
    /**book添加到购物车，死信交换机*/
    @Bean
    DirectExchange bookDeadDirectExchange(){
        return new DirectExchange(BOOK_DEAD_LETTER_EXCHANGE_NAME,true,false);
    }
    /**book添加到购物车，死信队列与交换机的绑定*/
    @Bean
    Binding bookDeadBinding(){
        return BindingBuilder.bind(bookDeadQueue()).to(bookDeadDirectExchange()).with(BOOK_DEAD_LETTER_ROUTING_KEY_NAME);
    }

    /**邮件队列*/
    @Bean
    Queue mailQueue(){
        //设置队列的生存时间
        //在消费端声明队列时，指定x-message-ttl参数，设置生存时间
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("x-message-ttl",60000);//60秒
        return new Queue(MAIT_QUEUE_NAME,true,false,false,map);
    }
    /**邮件交换机*/
    @Bean
    DirectExchange mailDirectExchange(){
        return new DirectExchange(MAIL_EXCHANGE_NAME,true,false);
    }
    /**绑定邮件队列与交换机*/
    @Bean
    Binding mailBinding(){
        return BindingBuilder.bind(mailQueue()).to(mailDirectExchange()).with(MAIL_ROUTING_KEY_NAME);
    }

}
