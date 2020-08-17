package com.example.workdemo5.repository;

import com.example.workdemo5.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories("com.example.workdemo5.repository")
public interface AdminRepository extends JpaRepository<BookEntity, Long> {

    /**根据图书id删除图书*/
    void delete(BookEntity bookEntity);
    /**更新图书*/

}
