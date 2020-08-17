package com.example.workdemo5.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class BookEntity implements Serializable {

    private static Long serialVersionUID = 11231231L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
    //当前页数
    @Transient
    private Integer currentPage;
    //每页展示条数
    @Transient
    private Integer pageSize;

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

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", author='" + author + '\'' +
                '}';
    }
}
