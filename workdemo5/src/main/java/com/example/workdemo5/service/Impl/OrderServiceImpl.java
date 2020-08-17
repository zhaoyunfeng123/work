package com.example.workdemo5.service.Impl;

import com.example.workdemo5.entity.OrderEntity;
import com.example.workdemo5.repository.OrderRepository;
import com.example.workdemo5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    /**保存orderID和userName*/
    public boolean saveOrderIdAndUserName(OrderEntity orderEntity) {
        try {
            orderRepository.save(orderEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**根据用户名查询所有订单*/
    public List<OrderEntity> getAllOrder(String userName) {
        return orderRepository.findByUserName(userName);
    }
}
