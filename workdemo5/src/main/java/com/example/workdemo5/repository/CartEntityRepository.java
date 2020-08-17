package com.example.workdemo5.repository;

import com.example.workdemo5.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@EnableJpaRepositories("com.example.workdemo5.repository")
public interface CartEntityRepository extends JpaRepository<CartEntity, Long> {
    //save方法
    //根据bookId删除购物车里指定的商品 delete(id)
//    void deleteByBookId(int bookId);  --自定义的这种方法不行？
    /**删除购物车里的所有商品*/
    @Transactional
    @Modifying
    @Query(value = "delete from cart_entity where user_name = ?1", nativeQuery = true)
    void deleteByUserName(String userName);

    /**根据用户名查询购物车里所有信息*/
    List<CartEntity> findByUserName(String userName);
    /**根据加入购物车时间和用户名查询信息*/
    CartEntity findByUserNameAndCreateCartTime(String userName, String createTime);
    /**根据cartId查询信息*/
    CartEntity findByCartId(int cartId);
    /**根据cartId删除信息*/
    @Transactional
    @Modifying
    @Query(value = "delete from cart_entity where cart_id = ?1", nativeQuery = true)
    void deleteByCartId(int cartId);
}
