package com.example.sample1.bean;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor //매개변수 있는 생성자
@NoArgsConstructor //매개변수 없는 생성자
public class PersonBean {

    private String id;
    private String name;
    private String addr;
    private int salary;
/*
    public PersonBean(String number, String 웬디, String 부산, int i) {
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", salary=" + salary +
                '}';
    }

    public void setName(String name) {
        this.name = "웬디"; // 별도로 만든 값이 더 우선순위 높음
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public int getSalary() {
        return salary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
*/

}
