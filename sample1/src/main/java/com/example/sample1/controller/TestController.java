package com.example.sample1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("test")
    public String test() {
        return "test"; //test get방식 요청 => resources/template/test.html
    }
}
