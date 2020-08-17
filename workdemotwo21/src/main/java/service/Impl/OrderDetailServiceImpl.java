package service.Impl;

import bean.OrderDetailBean;
import dao.OrderDetailDao;
import service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {
    /**
     * 保存订单详细信息
     * */
    @Override
    public boolean addOrderDetail(OrderDetailBean orderDetailBean) {
        OrderDetailDao orderDetailDao = new OrderDetailDao();
        return orderDetailDao.saveOrderDetail(orderDetailBean);
    }
}
