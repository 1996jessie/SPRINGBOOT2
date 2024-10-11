package com.example.sample3.repository;

import com.example.sample3.bean.BookBean;
import com.example.sample3.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {


    List<BookEntity> findByOrderByNoDesc();

    BookEntity findByNo(Integer no);


}
