package com.example.sample5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cafe")
public class CafeEntity {

    @Id
    @GeneratedValue
    private int num;

    private String id;
    private String name;
    private String menu;
    private String temp;
    private String amount;
    private String req;
}
