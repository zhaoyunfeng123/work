package com.example.workdemo5.repository;

import com.example.workdemo5.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories("com.example.workdemo5.repository")
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    //save()
}
