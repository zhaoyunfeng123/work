package service;

import bean.OrderBean;

import java.util.List;

public interface OrderService {
    /**
     * 保存订单
     * */
    boolean addOrder(OrderBean orderBean);
    /**
     * 查询我的订单
     * */
    List<OrderBean> selectAllOrder(String userName);
}
