package com.example.sample1.controller;

import com.example.sample1.bean.PersonBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class PersonController {

    @GetMapping("person")
    public PersonBean person() {
        PersonBean pb = new PersonBean("2", "웬디", "부산", 2000);
        /*
        pb.setId("1");
        pb.setName("아이유");
        pb.setAddr("서울");
        pb.setSalary(1000);
    */
        System.out.println(pb.toString());
        return pb; // JSON 타입으로 객체 자체를 리턴
    }
}
