package com.example.sample1.repository;

import com.example.sample1.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JPA : JAVA Persistence Api
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    //extends JpaRepository<ItemEntity, Long> : 테이블 만드는 엔티티 이름, pk의 클래스 타입

    List<ItemEntity> findByItemNm(String s); //미완성 메서드 만들기

    List<ItemEntity> findByPriceLessThanOrderByPriceDesc(int p);

    //@Query(value = "select * from item_entity", nativeQuery = true)
    @Query(value = "select i from ItemEntity i", nativeQuery = false) //* 대신 식별자 사용(나머지는 안 써도 됨) //JPQL(기존의 SQL과 다르면)
    List<ItemEntity> findAllQuery();

    //@Query(value = "select count(*) from item_entity", nativeQuery = true)
    @Query(value = "select count(*) from ItemEntity i")
    Integer findAllCountQuery();

    //@Query(value = "select count(*) from item_entity where item_detail like %:s%", nativeQuery = true)
    @Query(value = "select count(*) from ItemEntity where itemDetail like %:s%") // i.변수명
    Integer findAllCountQuery2(String s);

    //@Query(value = "select * from item_entity where item_detail like %:s%", nativeQuery = true)
    @Query(value = "select i from ItemEntity i where i.itemDetail like %:s%")
    List<ItemEntity> selectAllWithItemDetailText(String s);
}
