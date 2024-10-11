package com.example.sample5.bean;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CafeBean {
    private int num;

    @NotBlank (message = "아이디를 입력하세요")
    private String id;
    
    @NotBlank (message = "이름을 입력하세요")
    private String name;

    @NotBlank(message = "메뉴를 선택하세요")
    private String menu;

    @NotBlank(message = "온도를 선택하세요")
    private String temp;

    @NotBlank(message = "수량을 선택하세요")
    private String amount;

    @NotBlank(message = "하나라도 선택하세요")
    private String req;
}
