package com.example.workdemo5.service.Impl;

import com.example.workdemo5.entity.BookEntity;
import com.example.workdemo5.repository.AdminRepository;
import com.example.workdemo5.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    /**删除图书*/
    @Transactional
    public boolean deleteBook(BookEntity bookEntity) {
        try{
            adminRepository.delete(bookEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**更新图书*/
    @Transactional
    public boolean updateBook(BookEntity bookEntity) {
        try{
            adminRepository.save(bookEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**新增图书*/
    @Transactional
    public boolean addBook(BookEntity bookEntity) {
        try{
            adminRepository.save(bookEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
