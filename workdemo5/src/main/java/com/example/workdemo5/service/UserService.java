package com.example.workdemo5.service;

import com.example.workdemo5.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    /**登录*/
    boolean login(UserEntity userEntity);
    /**注册判断：用户是否已存在*/
    boolean registerExist(UserEntity userEntity);
    /**注册*/
    boolean register(UserEntity userEntity);
}
