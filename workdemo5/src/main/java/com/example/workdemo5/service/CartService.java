package com.example.workdemo5.service;

import com.example.workdemo5.entity.CartEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartService {
    /**添加到购物车*/
    boolean addBookToCart(CartEntity cartEntity);
    /**根据用户名查询购物车里所有信息*/
    List<CartEntity> selectAllGoods(String userName);
    /**根据bookId删除购物车里指定的商品*/
    boolean deleteBookFromCart(CartEntity cartEntity);
    /**删除购物车里面的所有商品*/
    boolean deleteAllBooksFromCart(String userName);
    /**根据加入购物车时间和用户名查询信息*/
    CartEntity selectBookByUserNameAndCreateTime(CartEntity cartEntity);
    /**根据cartId查询信息*/
    boolean selectBookCartByCartId(int cartId);
    /**根据cartId删除信息*/
    boolean deleteBookFromCartByCartId(int cartId);
}
