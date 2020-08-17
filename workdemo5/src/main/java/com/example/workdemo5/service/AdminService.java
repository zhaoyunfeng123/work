package com.example.workdemo5.service;

import com.example.workdemo5.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public interface AdminService {

    /**根据图书名称删除图书*/
    boolean deleteBook(BookEntity bookEntity);
    /**更新图书*/
    boolean updateBook(BookEntity bookEntity);
    /**新增图书*/
    boolean addBook(BookEntity bookEntity);
}
