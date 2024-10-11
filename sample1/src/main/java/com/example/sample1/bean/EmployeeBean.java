package com.example.sample1.bean;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeBean {
    private String id;
    private String company;
    private String part;
    private int hobong;

/*
    public EmployeeBean(String id, String company, String part, int hobong) {
        this.id = id;
        this.company = company;
        this.part = part;
        this.hobong = hobong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public int getHobong() {
        return hobong;
    }

    public void setHobong(int hobong) {
        this.hobong = hobong;
    }
*/

}
