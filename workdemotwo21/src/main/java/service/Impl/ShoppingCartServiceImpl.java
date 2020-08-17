package service.Impl;

import bean.ShoppingCartBean;
import dao.ShoppingCartDao;
import service.ShoppingCartService;

import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    /**
     * 添加购物车
     * */
    @Override
    public boolean addBook(ShoppingCartBean shoppingCartBean) {
        ShoppingCartDao cartDao = new ShoppingCartDao();
        return cartDao.saveCart(shoppingCartBean);
    }
    /**
     * 根据用户名查询购物车
     * */
    @Override
    public List<ShoppingCartBean> selectAllCart(String userName) {
        ShoppingCartDao cartDao = new ShoppingCartDao();
        return cartDao.findAllCartByUserName(userName);
    }
    /**
     * 清空购物车
     * */
    @Override
    public boolean deleteCart(String userName) {
        ShoppingCartDao cartDao = new ShoppingCartDao();
        return cartDao.removeCart(userName);
    }
    /**
     * 根据cartID和用户名删除购物车里的商品
     * */
    @Override
    public boolean deleteBookFromCartByCartIdAndUserName(String userName, int cartId) {
        ShoppingCartDao cartDao = new ShoppingCartDao();
        return cartDao.removeBookByCartIdAndUserName(userName, cartId);
    }
}
