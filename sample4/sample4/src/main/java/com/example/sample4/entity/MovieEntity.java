package com.example.sample4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "movie")
public class MovieEntity {

    @Id
    @GeneratedValue
    private int num;
    private String id;
    private String name;
    private Integer age;
    private String genre;
    private String time;
    private Integer partner;
    private String memo;

}
