package com.example.sample2.controller;

import com.example.sample2.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafController {

    //http://localhost:8080/thymeleaf/ex01
    @GetMapping(value = "/ex01")
    public String exam01(Model model){
        model.addAttribute("data","타임리프 1번 예제입니다.");
        model.addAttribute("name","아이유");
        return "viewEx01";
    }

    //http://localhost:8080/thymeleaf/ex01
    @GetMapping("/ex02")
    public String exam02(Model model){
        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);
        itemDto.setItemNm("상품");
        itemDto.setPrice(300);
        itemDto.setItemDetail("상품설명입니다.");
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto",itemDto);
        return "viewEx02";
    }

    @GetMapping("/ex03")
    public String exam03(Model model){
        List<ItemDto> itemDtoList = new ArrayList<ItemDto>();
        for(long i=1;i<=10;i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setId(i);
            itemDto.setItemNm("상품"+i);
            itemDto.setPrice(300);
            itemDto.setItemDetail("상품설명입니다.");
            itemDto.setRegTime(LocalDateTime.now());
            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList",itemDtoList);

        String[] arr = {"one","two","three"};
        model.addAttribute("arr",arr);

        return "viewEx03";
    }//exam03


    @GetMapping("/ex04")
    public String exam04(Model model){
        List<ItemDto> itemDtoList = new ArrayList<ItemDto>();
        for(long i=1;i<=10;i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setId(i);
            itemDto.setItemNm("상품"+i);
            itemDto.setPrice(300);
            itemDto.setItemDetail("상품설명입니다.");
            itemDto.setRegTime(LocalDateTime.now());
            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList",itemDtoList);

        return "thymeleaf/viewEx04";
    }//exam04

    @GetMapping("/ex05")
    public String exam05(Model model){
        List<ItemDto> itemDtoList = new ArrayList<ItemDto>();
        for(int i=1;i<=10;i++){
            ItemDto itemDto = new ItemDto();
//            itemDto.setId(i);
            itemDto.setItemNm("상품"+i);
            itemDto.setPrice(300*i);
            itemDto.setItemDetail("상품설명입니다.");
            itemDto.setRegTime(LocalDateTime.now());
            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList",itemDtoList);

        return "thymeleaf/viewEx05";
    }//exam05

    @GetMapping("/ex06")
    public String exam06(Model model){
        return "thymeleaf/viewEx06";
    }

    @GetMapping("/ex07")
    public String exam07(Model model){
        return "thymeleaf/viewEx07";
    }

    //http://localhost:8080/thymeleaf/ex08
    //http://localhost:8080/thymeleaf/ex08?param1=가나&param2=다라
    @GetMapping("/ex08")
    public String exam08(String param1, String param2, Model model){
        if(param1 == null){
            param1 = "하하하";
        }
        if(param2 == null){
            param2 = "호호호";
        }
        model.addAttribute("param1",param1);
        model.addAttribute("param2",param2);
        return "thymeleaf/viewEx08";
    }

    @GetMapping("/ex09")
    public String exam09(Model model){
        return "thymeleaf/viewEx09";
    }

    @GetMapping(value = "/first")
    public String first(Model model) {
        return "thymeleaf/first";
    }


    @GetMapping(value = "/second")
    public String second(Model model) {
        return "thymeleaf/second";
    }

    @GetMapping(value = "/third")
    public String third(Model model) {
        return "thymeleaf/third";
    }

}
