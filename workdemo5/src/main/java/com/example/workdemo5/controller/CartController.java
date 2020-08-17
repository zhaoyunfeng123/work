package com.example.workdemo5.controller;

import com.example.workdemo5.entity.CartEntity;
import com.example.workdemo5.service.CartService;
import com.example.workdemo5.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    CartService cartService;
    @Autowired
    SendService sendService;

    /**添加购物车*/
    @RequestMapping(value = "addToCart", consumes = "application/json")
    @ResponseBody
    public String addToCart(@RequestBody CartEntity cartEntity) {
        //通过request获取session中的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        cartEntity.setUserName(userName);
        //创建时间
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        cartEntity.setCreateCartTime(dateString);

        boolean result = cartService.addBookToCart(cartEntity);
        if (result){
            //查本条添加记录的cart_id
            CartEntity c = cartService.selectBookByUserNameAndCreateTime(cartEntity);
            int cartId = c.getCartId();
            //传递给book_queue
            sendService.sendCartIdQueue(cartId);
            return "success";
        }
        return "fail";
    }

    /**根据用户名查询购物车里所有信息*/
    @RequestMapping(value = "selectAllFromCart", consumes = "application/json")
    @ResponseBody
    public Map<String, Object> selectAllFromCart(){
        //通过request获取session中的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        List<CartEntity> cartEntityList = cartService.selectAllGoods(userName);
        Map<String, Object> map = new HashMap<String, Object>();
        if (cartEntityList != null){
            map.put("cartEntity",cartEntityList);
            map.put("result","success");
        }else {
            map.put("result","error");
        }
        return map;
    }

    /**根据bookId删除购物车里的商品*/
    @RequestMapping(value = "deleteFromCart", consumes = "application/json")
    @ResponseBody
    public String deleteFromCart(@RequestBody int cartId){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartId(cartId);
        System.out.println(cartEntity.toString());
        boolean result = cartService.deleteBookFromCart(cartEntity);
        if (result){
            return "success";
        }
        return "fail";
    }
    /**删除购物车里面的所有商品*/
    @RequestMapping(value = "deleteAllFromCart", consumes = "application/json")
    @ResponseBody
    public String deleteAllFromCart(){
        //通过request获取session中的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        boolean result = cartService.deleteAllBooksFromCart(userName);
        if (result){
            return "success";
        }
        return "fail";
    }
}
