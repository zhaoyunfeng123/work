package com.example.workdemo5.service;

import com.example.workdemo5.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookService {

    /**查询所有图书*/
    List<BookEntity> selectALL();
//    /**根据书名或作者查询图书*/
//    List<BookEntity> selectBookNameOrAuthor(BookEntity bookEntity);
    /**分页查询所有图书*/
    Page<BookEntity> getBookPage(int currentPage, int pageSize);
    /**根据书名模糊查询图书*/
    List<BookEntity> getBookByBookName(BookEntity bookEntity);
    /**根据作者模糊查询图书*/
    List<BookEntity> getBookByAuthor(BookEntity bookEntity);
    /**根据书名和作者模糊查询图书*/
    List<BookEntity> getBookByBookNameAndAuthor(BookEntity bookEntity);
}
