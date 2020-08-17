package com.example.workdemo5.controller;

import com.example.workdemo5.entity.BookEntity;
import com.example.workdemo5.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    HttpServletRequest request;

    /**查询所有图书*/
//    @RequestMapping(value = "listAllBook", consumes = "application/json")
//    public List<BookEntity> selectALLBook(){
//        List<BookEntity> listBook = bookService.selectALL();
//        return listBook;
//    }

    /**根据书名或作者查询图书*/
    @RequestMapping(value = "selectIndex", consumes = "application/json")
    @ResponseBody
    public List<BookEntity> selectIndex(@RequestBody BookEntity bookEntity){
        List<BookEntity> listBooks = new ArrayList<BookEntity>();
        String bookName = bookEntity.getBookName();
        String author = bookEntity.getAuthor();
        //通过request获取session中的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        //输入内容为空时查询所有图书
        if (bookName == "" && author == ""){
            listBooks = bookService.selectALL();
        }
        //根据输入的书名模糊查询图书
        if (bookName != "" && author == ""){
            listBooks = bookService.getBookByBookName(bookEntity);
        }
        //根据输入的书名模糊查询图书
        if (bookName == "" && author != ""){
            listBooks = bookService.getBookByAuthor(bookEntity);
        }
        //根据书名和作者模糊查询图书
        if (bookName != "" && author != ""){
            listBooks = bookService.getBookByBookNameAndAuthor(bookEntity);
        }
       return listBooks;
    }

    /**分页查询所有图书的实现*/
    @RequestMapping(value = "listAllBook", consumes = "application/json")
    @ResponseBody
    public Map<String,Object> getBookIndex(@RequestBody BookEntity bookEntity){
        //通过request获取session中的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        int currentPage = bookEntity.getCurrentPage();
        int pageSize = bookEntity.getPageSize();
        currentPage = currentPage -1;
        //设置默认每页展示条数
        Page<BookEntity> page = bookService.getBookPage(currentPage, pageSize);
        //总页数
        int totalPage = page.getTotalPages();
        //每页数据
        List<BookEntity> listBook = page.getContent();
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("totalPage",totalPage);
        m.put("listBook",listBook);
        return m;
    }
}
