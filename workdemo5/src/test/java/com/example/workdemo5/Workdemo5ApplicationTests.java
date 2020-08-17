package com.example.workdemo5;

import com.example.workdemo5.entity.*;
import com.example.workdemo5.redis.RedisUtil;
import com.example.workdemo5.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Workdemo5ApplicationTests {

    @Autowired
    BookService bookService;
    @Autowired
    AdminService adminService;
    @Autowired
    CartService cartService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SendService sendService;
    @Autowired
    OrderService orderService;
    @Test
    void contextLoads() {
        //redis测试
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserName("abcabcabc123");
//        userEntity.setPassword("abcabcabc123");
//
////        redisUtil.saveOne(userEntity.getUserName(), userEntity.getUserName());
//        Object s = redisUtil.findOne(userEntity.getUserName());
//        System.out.println(s.toString());
//        //mail测试
//        MailEntity mailEntity = new MailEntity();
//        mailEntity.setTo("1546419171@qq.com");
//        mailEntity.setTitle("书香商城");
//        mailEntity.setContent("欢迎您注册书香商城");
//        sendService.sendMailToQueue(mailEntity);
//        int cartId = 70;
//        boolean r = cartService.selectBookCartByCartId(cartId);
//        if(r){
//            System.out.println("查询成功-----"+cartId);
//        }else {
//            System.out.println("查询失败-----"+cartId);
//        }
//        boolean n = cartService.deleteBookFromCartByCartId(69);
//        if(n){
//            System.out.println("删除成功-----"+69);
//        }else {
//            System.out.println("删除失败-----"+cartId);
//        }
//        sendService.sendCartIdQueue(71);

//        List<OrderEntity> listOrder = orderService.getAllOrder("abc77777ABC");
//        List<OrderDetailEntity> detailList = new ArrayList<OrderDetailEntity>();
//        for (OrderEntity o :listOrder) {
////            System.out.println(o.toString());
//
//            detailList = o.getOrderDetailEntityList();
//            detailList.toString();
        }
//        for (OrderDetailEntity detail:detailList
//        ) {

//            System.out.println("55555555555555："+detailList.toString());
//        for (OrderDetailEntity d:detailList
//             ) {
//            System.out.println("------------------："+d.toString());
//        }
//        }
//    }

}
