package com.example.sample4.repository;

import com.example.sample4.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    List<MovieEntity> findByOrderByNumDesc();

    MovieEntity findMovieById(String id);

    MovieEntity findByNum(int num);

    Page<MovieEntity> findByNameContainingOrGenreContainingOrderByNumDesc(String keyword, String keyword1, Pageable pageable);

    //sql,jpql
    //@Query(value = "select * from movie where name like %:keyword% or genre like %:keyword% order by num desc", nativeQuery = true)
    @Query(value = "select m from MovieEntity m where m.name like %:keyword% or m.genre like %:keyword% order by m.num desc")
    Page<MovieEntity> findByNameGenreOrder(String keyword, Pageable pageable);
}