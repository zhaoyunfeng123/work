package com.example.workdemo5.service.Impl;

import com.example.workdemo5.entity.UserEntity;
import com.example.workdemo5.repository.UserEntityRepository;
import com.example.workdemo5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实现登录和注册的类
 * */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    /**登录*/
    public boolean login(UserEntity userEntity) {
        UserEntity u = userEntityRepository.findByUserNameAndPasswordAndUserType(userEntity.getUserName(), userEntity.getPassword(), userEntity.getUserType());
        if (u == null){
            return false;
        }
        return true;
    }

    /**注册前，判断该用户是否已存在*/
    public boolean registerExist(UserEntity userEntity) {
        UserEntity user = userEntityRepository.findByUserName(userEntity.getUserName());
        if (user == null){
            //该用户未注册
            return true;
        }
        return false;
    }

    /**注册*/
    @Transactional
    public boolean register(UserEntity userEntity) {
        userEntityRepository.save(userEntity);
        return true;
    }
}
