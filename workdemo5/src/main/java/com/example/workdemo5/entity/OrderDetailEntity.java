package com.example.workdemo5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderDetailEntity implements Serializable {

    private static Long serialVersionUID = 256231231L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    //书名
    private Integer bookId;
    //书名
    @Column(length = 20)
    private String bookName;
    //单价
    private Double price;
    //作者
    @Column(length = 20)
    private String author;
    //购买数量
    private Integer number;
    //订单ID
    @Column(length = 20,name = "order_id")
    private String orderId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id",insertable = false,updatable = false,referencedColumnName = "order_id")
    private OrderEntity orderEntity;

    @Override
    public String toString() {
        return "OrderDetailEntity{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", number=" + number +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
