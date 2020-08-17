package com.example.workdemo5.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartEntity implements Serializable {

    private static Long serialVersionUID = 11231231L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer cartId;
    //用户名
    @Column(length = 20)
    private String userName;
    //书ID
    private Integer bookId;
    //书名
    @Column(length = 20)
    private String bookName;
    //价格
    private Double price;
    //库存
    private Integer stock;
    //作者
    @Column(length = 20)
    private String author;
    //购买数量
    private Integer number;
    //收货人姓名
    @Column(length = 20)
    private String cartName;
    //收货人电话
    private Integer cartTel;
    //收货人地址
    @Column(length = 20)
    private String cartRddress;
    //创建时间
    @Column(length = 20)
    private String createCartTime;

    @Override
    public String toString() {
        return "CartEntity{" +
                "cartId=" + cartId +
                ", userName='" + userName + '\'' +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", author='" + author + '\'' +
                ", number=" + number +
                ", cartName='" + cartName + '\'' +
                ", cartTel=" + cartTel +
                ", cartRddress='" + cartRddress + '\'' +
                ", createCartTime='" + createCartTime + '\'' +
                '}';
    }

    public String getCreateCartTime() {
        return createCartTime;
    }

    public void setCreateCartTime(String createCartTime) {
        this.createCartTime = createCartTime;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public Integer getCartTel() {
        return cartTel;
    }

    public void setCartTel(Integer cartTel) {
        this.cartTel = cartTel;
    }

    public String getCartRddress() {
        return cartRddress;
    }

    public void setCartRddress(String cartRddress) {
        this.cartRddress = cartRddress;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
}
