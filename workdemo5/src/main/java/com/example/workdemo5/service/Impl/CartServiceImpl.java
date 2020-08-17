package com.example.workdemo5.service.Impl;

import com.example.workdemo5.entity.CartEntity;
import com.example.workdemo5.repository.CartEntityRepository;
import com.example.workdemo5.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartEntityRepository cartEntityRepository;

    /**添加到购物车*/
    @Transactional
    public boolean addBookToCart(CartEntity cartEntity) {
        try{
            cartEntityRepository.save(cartEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**根据用户名查询购物车里所有信息*/
    public List<CartEntity> selectAllGoods(String userName) {
        return cartEntityRepository.findByUserName(userName);
    }
    /**根据bookId删除购物车里指定的商品*/
    public boolean deleteBookFromCart(CartEntity cartEntity) {
        try{
            cartEntityRepository.delete(cartEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**删除购物车里面的所有商品*/
    @Transactional
    public boolean deleteAllBooksFromCart(String userName) {
        try{
            cartEntityRepository.deleteByUserName(userName);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**根据加入购物车时间和用户名查询信息*/
    public CartEntity selectBookByUserNameAndCreateTime(CartEntity cartEntity) {
        try{
            return cartEntityRepository.findByUserNameAndCreateCartTime(cartEntity.getUserName(), cartEntity.getCreateCartTime());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**根据cartId查询信息*/
    public boolean selectBookCartByCartId(int cartId) {
        CartEntity cartEntity = cartEntityRepository.findByCartId(cartId);
        if (cartEntity != null){
            return true;
        }
        return false;
    }
    /**根据cartId删除信息*/
    public boolean deleteBookFromCartByCartId(int cartId) {
        try{
            cartEntityRepository.deleteByCartId(cartId);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
