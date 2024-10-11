package com.example.sample3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue
    private int no;
    private String title;
    private String author;
    private String publisher;
    private Integer price;
    private String buy;
    private String kind;
    private String bookstore;
    private Integer count;
}
