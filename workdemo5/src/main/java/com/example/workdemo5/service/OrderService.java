package com.example.workdemo5.service;

import com.example.workdemo5.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderService {
    /**保存orderID和userName*/
    boolean saveOrderIdAndUserName(OrderEntity orderEntity);
    /**根据用户名查询所有订单*/
    List<OrderEntity> getAllOrder(String userName);
}
