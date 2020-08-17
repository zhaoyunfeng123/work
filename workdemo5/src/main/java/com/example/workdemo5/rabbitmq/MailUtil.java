package com.example.workdemo5.rabbitmq;

import com.example.workdemo5.entity.MailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;
    /**
     * 发送简单邮件
     * */
    public boolean send(MailEntity mail){
        //目标邮箱
        String to = mail.getTo();
        //邮件标题
        String title = mail.getTitle();
        //邮件正文
        String content = mail.getContent();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(title);
        mailMessage.setText(content);

        try{
            Long begin = System.currentTimeMillis();
            javaMailSender.send(mailMessage);
            Long end = System.currentTimeMillis();
            System.out.println("邮件发送成功，用时："+ (end - begin) + "ms");
            return true;
        }catch (MailException e){
            System.out.println("邮件发送失败");
            return false;
        }
    }
}
