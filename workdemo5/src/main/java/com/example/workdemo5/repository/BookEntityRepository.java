package com.example.workdemo5.repository;

import com.example.workdemo5.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories("com.example.workdemo5.repository")
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {

    /**查询所有图书*/
    List<BookEntity> findAll();
    /**分页查询所有图书*/
    Page<BookEntity> findAll(Pageable pageable);
//    /**根据书名或作者查询图书：精确查找*/
//    List<BookEntity> findByBookNameOrAuthor(String bookName, String author);
    /**根据书名模糊查询图书*/
    List<BookEntity> findByBookNameContaining(String bookName);
    /**根据作者模糊查询图书*/
    List<BookEntity> findByAuthorContaining(String author);
    /**根据书名和作者模糊查询图书*/
    List<BookEntity> findByBookNameContainingAndAuthorContaining(String bookName, String author);
    //save方法

}
