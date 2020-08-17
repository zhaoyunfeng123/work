package com.example.workdemo5.service.Impl;

import com.example.workdemo5.entity.BookEntity;
import com.example.workdemo5.repository.BookEntityRepository;
import com.example.workdemo5.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookEntityRepository bookEntityRepository;

    /**查询所有图书的实现*/
    public List<BookEntity> selectALL() {
        return bookEntityRepository.findAll();
    }
//    /**根据书名或作者查询图书的实现*/
//    public List<BookEntity> selectBookNameOrAuthor(BookEntity bookEntity) {
//        return bookEntityRepository.findByBookNameOrAuthor(bookEntity.getBookName(), bookEntity.getAuthor());
//    }
    /**分页查询所有图书的实现*/
    public Page<BookEntity> getBookPage(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return bookEntityRepository.findAll(pageable);
    }
    /**根据书名模糊查询图书*/
    public List<BookEntity> getBookByBookName(BookEntity bookEntity) {
        return bookEntityRepository.findByBookNameContaining(bookEntity.getBookName());
    }
    /**根据作者模糊查询图书*/
    public List<BookEntity> getBookByAuthor(BookEntity bookEntity) {
        return bookEntityRepository.findByAuthorContaining(bookEntity.getAuthor());
    }
    /**根据书名和作者模糊查询图书的实现*/
    public List<BookEntity> getBookByBookNameAndAuthor(BookEntity bookEntity) {
        return bookEntityRepository.findByBookNameContainingAndAuthorContaining(bookEntity.getBookName(), bookEntity.getAuthor());
    }
}
