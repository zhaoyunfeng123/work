package com.example.workdemo5.controller;

import com.example.workdemo5.entity.CartEntity;
import com.example.workdemo5.entity.OrderDetailEntity;
import com.example.workdemo5.entity.OrderEntity;
import com.example.workdemo5.service.CartService;
import com.example.workdemo5.service.OrderDetailService;
import com.example.workdemo5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrderController {
    private static String date;
    private static Long orderNum = 0L;
    @Autowired
    HttpServletRequest request;
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDetailService orderDetailService;

    /**结算订单*/
    @RequestMapping(value = "countCartGoods", consumes = "application/json")
    @ResponseBody
    public String countCartGoods(@RequestBody OrderEntity orderEntity){
        //通过request获取session中的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        //订单时间
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        //订单表：保存订单id和username
        String orderID = OrderController.getOrderNum();
        orderEntity.setOrderId(orderID);
        orderEntity.setUserName(userName);
        orderEntity.setCreateCartTime(dateString);
        List<OrderDetailEntity>  detailEntityList = new ArrayList<OrderDetailEntity>();
        //根据用户名查询购物车里的所有商品
        List<CartEntity> cartEntityList = cartService.selectAllGoods(userName);
        for (CartEntity c : cartEntityList) {
            //购物车商品详情
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setOrderId(orderEntity.getOrderId());
            orderDetailEntity.setBookId(c.getBookId());
            orderDetailEntity.setBookName(c.getBookName());
            orderDetailEntity.setPrice(c.getPrice());
            orderDetailEntity.setAuthor(c.getAuthor());
            orderDetailEntity.setNumber(c.getNumber());

            detailEntityList.add(orderDetailEntity);
        }
        orderEntity.setOrderDetailEntityList(detailEntityList);
        boolean overbooking = orderService.saveOrderIdAndUserName(orderEntity);
        if (overbooking){
            //结算成功后，删除购物车里的所有商品
            boolean result = cartService.deleteAllBooksFromCart(userName);
            if (result){
                return "success";
            }else {
                return "fail";
            }
        }
        return "fail";
    }
    /**查询所有订单*/
    @RequestMapping(value = "selectAllOrder", consumes = "application/json")
    @ResponseBody
    public Map<String, Object> countCartGoods(){
        //通过request获取session中的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        List<OrderEntity> orderList = orderService.getAllOrder(userName);
        System.out.println(orderList.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderList",orderList);
        return map;
    }

    //生成订单编号
    public static synchronized String getOrderNum(){
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if (date == null || !date.equals(str)){
            date = str;
            orderNum = 0L;
        }
        orderNum ++;
        Long orderNo = Long.parseLong((date))*10000;
        orderNo += orderNum;
        return orderNo+"";
    }

}
