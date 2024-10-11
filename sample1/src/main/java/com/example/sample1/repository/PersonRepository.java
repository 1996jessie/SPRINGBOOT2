package com.example.sample1.repository;

import com.example.sample1.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    List<PersonEntity> findByName(String name);

    List<PersonEntity> findBySalaryLessThan(int salary);

    @Query(value = "select * from person where name = :n", nativeQuery = true)
    List<PersonEntity> findByName2(String n);

    @Query(value = "select count(*) from person where name like %:n%", nativeQuery = true)
    int countNameWith(String n);

    @Query(value = "select p from PersonEntity p where p.name like %:n%", nativeQuery = false)
    List<PersonEntity> findNameWith(String n);
}
