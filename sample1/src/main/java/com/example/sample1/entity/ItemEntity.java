package com.example.sample1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity //jakarta.persistence package
@Setter
@Getter
@ToString
//@Table(name = "xxx") // 테이블 이름 바꾸기
public class ItemEntity {
    @Id //@id : PK
    @Column(name = "item_id") //굳이 안 써도 변수 만들어짐 //칼럼명 바꾸고 싶으면 이렇게
    @GeneratedValue// 시퀀스 처리해서 하나씩 증가
    private Long id;

    @Column(name = "itemName", nullable = false, length = 10) //String은 기본으로 null >> Not null //length : 길이
    private String itemNm;

    private int price;

    @Column(nullable = false)
    private String itemDetail;
    private LocalDateTime regTime;

}

/*

create table item_entity ( //대문자 앞에 밑줄 생김
    price number(10,0) not null,
    id number(19,0) not null, //@id : PK
    reg_time timestamp(6),
    item_detail varchar2(255 char),
    item_nm varchar2(255 char),
    primary key (id)
)

*/
