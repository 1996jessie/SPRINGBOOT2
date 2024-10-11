package com.example.sample1.controller;

import com.example.sample1.bean.EmployeeBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
@RestController
public class EmployeeController {

    @GetMapping("employee")
    public EmployeeBean employee() {
        //EmployeeBean eb = new EmployeeBean("lee", "samsung", "CEO", 10000);

        EmployeeBean eb = new EmployeeBean();
        eb.setId("koo");
        eb.setCompany("LG");
        eb.setPart("CEO");
        eb.setHobong(10000);


        return eb;
    }
}
