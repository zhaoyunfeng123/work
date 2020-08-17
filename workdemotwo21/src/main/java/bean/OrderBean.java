package bean;

import java.util.List;

public class OrderBean {
    //订单id
    private String orderId;
    //用户名
    private String userName;
    //地址
    private String address;
    //总价
    private Double count;
    //下单时间
    private String createCartTime;
    //orderDetail集合
    private List<OrderDetailBean> orderDetailBeanList;

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderId='" + orderId + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", count=" + count +
                ", createCartTime='" + createCartTime + '\'' +
                ", orderDetailBeanList=" + orderDetailBeanList +
                '}';
    }

    public List<OrderDetailBean> getOrderDetailBeanList() {
        return orderDetailBeanList;
    }

    public void setOrderDetailBeanList(List<OrderDetailBean> orderDetailBeanList) {
        this.orderDetailBeanList = orderDetailBeanList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getCreateCartTime() {
        return createCartTime;
    }

    public void setCreateCartTime(String createCartTime) {
        this.createCartTime = createCartTime;
    }
}
