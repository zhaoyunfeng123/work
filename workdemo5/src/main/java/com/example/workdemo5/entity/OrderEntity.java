package com.example.workdemo5.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class OrderEntity implements Serializable {

    private static Long serialVersionUID = 119685231L;

    @Id
    @Column(length = 20,name = "order_id")
    private String orderId;
    @Column(length = 20)
    private String userName;
    //总价
    private Double count;
    //收货人姓名
    @Column(length = 20)
    private String addressName;
    //收货人电话
    private String addressTel;
    //收货人地址
    @Column(length = 20)
    private String address;
    //创建时间
    @Column(length = 20)
    private String createCartTime;
//    @JsonIgnore
//FetchType.EAGER，表示取出这条数据时，它关联的数据也同时取出放入内存中
//FetchType.LAZY，取出这条数据时，它关联的数据并不取出来，在同一个session中，什么时候要用，就什么时候取(再次访问数据库)。
    @OneToMany(targetEntity = OrderDetailEntity.class, mappedBy = "orderId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetailEntityList;


    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId='" + orderId + '\'' +
                ", userName='" + userName + '\'' +
                ", count=" + count +
                ", addressName='" + addressName + '\'' +
                ", addressTel='" + addressTel + '\'' +
                ", address='" + address + '\'' +
                ", createCartTime='" + createCartTime + '\'' +
                ", orderDetailEntityList=" + orderDetailEntityList +
                '}';
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressTel() {
        return addressTel;
    }

    public void setAddressTel(String addressTel) {
        this.addressTel = addressTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateCartTime() {
        return createCartTime;
    }

    public void setCreateCartTime(String createCartTime) {
        this.createCartTime = createCartTime;
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

    public List<OrderDetailEntity> getOrderDetailEntityList() {
        return orderDetailEntityList;
    }

    public void setOrderDetailEntityList(List<OrderDetailEntity> orderDetailEntityList) {
        this.orderDetailEntityList = orderDetailEntityList;
    }
}
