package com.example.workdemo5.repository;

import com.example.workdemo5.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories("com.example.workdemo5.repository")
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    /**登录查询*/
    UserEntity findByUserNameAndPasswordAndUserType(String userName, String password, int userType);
    /**注册查询*/
    UserEntity findByUserName(String userName);
    //save方法

}
