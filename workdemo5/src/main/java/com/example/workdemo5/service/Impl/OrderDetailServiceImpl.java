package com.example.workdemo5.service.Impl;

import com.example.workdemo5.entity.OrderDetailEntity;
import com.example.workdemo5.repository.OrderDetailRepository;
import com.example.workdemo5.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {


    @Autowired
    OrderDetailRepository orderDetailRepository;
    /**保存订单详情*/
    public boolean saveOrderDetail(OrderDetailEntity orderDetailEntity) {
        try {
            orderDetailRepository.save(orderDetailEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
