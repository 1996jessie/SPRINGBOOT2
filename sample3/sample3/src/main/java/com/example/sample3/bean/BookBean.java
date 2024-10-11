package com.example.sample3.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookBean {
    private int no;

    @NotEmpty(message = "제목 필수 입력")
    private String title;
    @NotEmpty(message = "저자 필수 입력")
    private String author;
    @NotEmpty(message = "출판사 필수 입력")
    private String publisher;
    @NotNull(message = "가격 필수 입력")
    private Integer price;
    @NotEmpty(message = "입고일 필수 입력")
    private String buy;
    @NotEmpty(message = "배송비 필수 입력")
    private String kind;
    @NotEmpty(message = "구입 가능 서점 필수 입력")
    private String bookstore;
    @NotNull(message = "보유수량 필수 입력")
    private Integer count;
}
