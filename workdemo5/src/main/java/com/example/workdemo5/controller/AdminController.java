package com.example.workdemo5.controller;

import com.example.workdemo5.entity.BookEntity;
import com.example.workdemo5.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    HttpServletRequest request;


    @RequestMapping(value = "deleteBook", consumes = "application/json")
    @ResponseBody
    /**删除图书*/
    public String deleteBooks(@RequestBody BookEntity bookEntity){
        //通过request获取session中的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        boolean result = adminService.deleteBook(bookEntity);
        if (result){
            return "success";
        }
        return "fail";
    }
    @RequestMapping(value = "updateBook", consumes = "application/json")
    @ResponseBody
    /**更新图书*/
    public String updateBooks(@RequestBody BookEntity bookEntity){
        //通过request获取session中的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        boolean result = adminService.updateBook(bookEntity);
        if (result){
            return "success";
        }
        return "fail";
    }
    @RequestMapping(value = "addBookToDatabase", consumes = "application/json")
    @ResponseBody
    /**新增图书*/
    public String addBooks(@RequestBody BookEntity bookEntity){
        //通过request获取session中的用户名
        String userName = (String) request.getSession().getAttribute("userName");
        //新增内容检测
        System.out.println(bookEntity.toString());
        boolean result = adminService.addBook(bookEntity);
        if (result){
            return "success";
        }
        return "fail";
    }
    /**新增内容检测*/
//    public boolean checkBooks(BookEntity bookEntity){
//        String name = bookEntity.getBookName();
//        double price = bookEntity.getPrice();
//        int stock = bookEntity.getStock();
//        String author = bookEntity.getAuthor();
//        if (name == "" ||  author == ""){
//            return false;
//        }
//        return true;
//    }
}
