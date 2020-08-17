package service.Impl;

import bean.OrderBean;
import dao.OrderDao;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    /**
     * 保存订单
     * */
    @Override
    public boolean addOrder(OrderBean orderBean) {
        OrderDao orderDao = new OrderDao();
        return orderDao.saveOrder(orderBean);
    }
    /**
     * 查询我的订单
     * */
    @Override
    public List<OrderBean> selectAllOrder(String userName) {
        OrderDao orderDao = new OrderDao();
        return orderDao.findAllOrder(userName);
    }
}
