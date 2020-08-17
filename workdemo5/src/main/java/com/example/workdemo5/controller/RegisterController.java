package com.example.workdemo5.controller;

import com.example.workdemo5.entity.MailEntity;
import com.example.workdemo5.entity.UserEntity;
import com.example.workdemo5.redis.RedisUtil;
import com.example.workdemo5.service.SendService;
import com.example.workdemo5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private SendService sendService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "register", consumes = "application/json")
    @ResponseBody
    public String userRegister(@RequestBody UserEntity user){
        //判断注册信息是否有为空
        boolean registerStatus = validate(user);
        if (registerStatus){
            //判断该注册用户是否已存在
            boolean exist = userService.registerExist(user);
            if (exist){
                //该用户不存在，则注册
                boolean result = regist(user);
                redisUtil.saveOne(user.getUserName(), user.getPassword());
                if (result){
                    return "success";
                }
                return "errorFail";
            }
            return "errorExist";
        }
        return "errorNull";
    }
    /**执行注册*/
    public boolean regist(UserEntity user){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        //为注册者添加用户身份类别（0：管理员，1：用户）
        user.setUserType(1);
        //设置注册时间
        user.setCreateTime(dateString);
        //发送邮件
        String email = user.getEmail();
        MailEntity mailEntity = new MailEntity();
        mailEntity.setTo(email);
        mailEntity.setTitle("书香商城");
        mailEntity.setContent("欢迎您注册书香商城111");
        try{
            sendService.sendMailToQueue(mailEntity);
            //返回信息
            return userService.register(user);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**判断注册信息是否有为空*/
    public boolean validate(UserEntity userEntity){
        String username = userEntity.getUserName();
        String password = userEntity.getPassword();
        String sex = userEntity.getSex();
        String email = userEntity.getEmail();
        if(username != null && password != null && sex != null && email != null){
            return true;
        }
        return false;
    }
}
