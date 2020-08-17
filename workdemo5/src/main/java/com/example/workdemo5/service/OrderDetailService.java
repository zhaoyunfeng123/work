package com.example.workdemo5.service;

import com.example.workdemo5.entity.OrderDetailEntity;
import org.springframework.stereotype.Component;

@Component
public interface OrderDetailService {
    /**保存订单详情*/
    boolean saveOrderDetail(OrderDetailEntity orderDetailEntity);
}
