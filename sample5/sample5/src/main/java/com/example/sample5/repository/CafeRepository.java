package com.example.sample5.repository;

import com.example.sample5.entity.CafeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CafeRepository extends JpaRepository<CafeEntity, Integer> {

    List<CafeEntity> findByOrderByNumDesc();

    CafeEntity findCafeById(String id);

    CafeEntity findByNum(int num);

    Page<CafeEntity> findByNameContainingOrTempContainingOrderByNumDesc(String keyword, String keyword1, Pageable pageable);

    //sql,jpql
    //@Query(value = "select * from movie where name like %:keyword% or genre like %:keyword% order by num desc", nativeQuery = true)
    @Query(value = "select cafe from CafeEntity cafe where cafe.name like %:keyword% or cafe.temp like %:keyword% order by cafe.num desc")
    Page<CafeEntity> findByNameTempOrder(String keyword, Pageable pageable);
}