package service;

import bean.OrderDetailBean;

public interface OrderDetailService {
    /**
     * 保存订单详细信息
     * */
    boolean addOrderDetail(OrderDetailBean orderDetailBean);

}
