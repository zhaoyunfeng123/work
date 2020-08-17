package service;

import bean.ShoppingCartBean;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 添加购物车
     * */
    boolean addBook(ShoppingCartBean shoppingCartBean);
    /**
     * 根据用户名查询购物车
     * */
    List<ShoppingCartBean> selectAllCart(String userName);
    /**
     * 清空购物车
     * */
    boolean deleteCart(String userName);
    /**
     * 根据cartID和用户名删除购物车里的商品
     * */
    boolean deleteBookFromCartByCartIdAndUserName(String userName, int cartId);
}
