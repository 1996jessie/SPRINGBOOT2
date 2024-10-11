package com.example.sample4.bean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieBean {
    private int num;

    @NotEmpty(message = "아이디 필수 입력")
    private String id;
    @NotEmpty(message = "이름 필수 입력")
    private String name;
    @NotNull(message = "나이 필수 입력")
    private Integer age;
    @NotNull(message = "장르 필수 입력")
    private String genre;
    @NotEmpty(message = "시간 필수 입력")
    private String time;
    @NotNull(message = "동반관객 필수 입력")
    private Integer partner;
    @NotEmpty(message = "개선사항 필수 입력")
    private String memo;
}
