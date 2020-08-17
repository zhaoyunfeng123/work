package com.example.workdemo5.repository;

import com.example.workdemo5.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories("com.example.workdemo5.repository")
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    //保存订单
    //save()
    /**根据用户忙查询所有订单*/
    List<OrderEntity> findByUserName(String userName);
}
