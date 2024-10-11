package com.example.sample5.controller;


import com.example.sample5.bean.CafeBean;
import com.example.sample5.entity.CafeEntity;
import com.example.sample5.service.CafeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;



    /*검색어, 페이지 설정*/
    @GetMapping(value = {"/","/cafe"} ) // 목록 조회, value 생략 가능
    public String selectAll(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "3") int size,
                            @RequestParam(required = false) String keyword,
                            Model model){

        long totalCount =  cafeService.getTotalCount();
        Page<CafeEntity> cafeList = cafeService.getCafeEntity(page,size,keyword);


        System.out.println("keyword : " + keyword);
        System.out.println("page : " + page);
        System.out.println("size : " + size);
        System.out.println("cafeList.getSize() :"+ cafeList.getSize()); //
        System.out.println("cafeList.getTotalElements() :"+ cafeList.getTotalElements()); // 전체, 검색
        System.out.println("cafeList.getNumberOfElements() :"+ cafeList.getNumberOfElements());
        System.out.println("cafeList.getNumber() :"+ cafeList.getNumber()); // p1=>0, p2=>1
        System.out.println("cafeList.getTotalPages() :"+ cafeList.getTotalPages()); //

        System.out.println("totalCount :"+ totalCount);


        model.addAttribute("cafeList", cafeList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("getTotalElements", cafeList.getTotalElements());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);

        return "/cafe/select";
    }

    @GetMapping(value = "/cafe/insert")
    public String insertGet(Model model){
        System.out.println("/cafe/insert get방식");
        model.addAttribute("cafeBean", new CafeBean());

        return "cafe/insert";
    }//insertGet

    @PostMapping(value="/cafe/insert")
    public String insertCafePost(@ModelAttribute("cafeBean") @Valid CafeBean cafeBean , BindingResult result,
                                  Model model){

        if(result.hasErrors()){
            model.addAttribute("cafeBean", cafeBean);
            return "cafe/insert";
        }

        CafeEntity cafeEntity = cafeService.insertCafe(cafeBean);
        cafeService.saveCafe(cafeEntity);

        return "redirect:/";
    }


    @PostMapping(value="/cafe/idcheck")
    @ResponseBody
    public String idcheck(@RequestParam("id") String id) {
        System.out.println("/cafe/idcheck/id: " + id);
        CafeEntity cafe = cafeService.findCafeById(id);
        System.out.println(cafe);
        return (cafe == null) ? "YES" : "NO";
    }



    @GetMapping("/cafe/detail/{num}")
    public String detail(@PathVariable("num") int num, Model model){

        CafeEntity cafeEntity = cafeService.selectCafeByNum(num);

        model.addAttribute("cafe", cafeEntity);

        return "cafe/detail";
    }

    @GetMapping("/cafe/delete")
    public String delete(@RequestParam("num") int num){
        cafeService.deleteById(num);
        return "redirect:/";
    }

    @PostMapping(value="/cafe/checkDelete")
    public String checkDelete(@RequestParam("row") int[] row) {
        for(int i=0; i<row.length; i++) {
            cafeService.deleteById(row[i]);
        }

        return "redirect:/";
    }

    @GetMapping("/cafe/update")
    public String update(@RequestParam("num") int num, Model model){
        CafeEntity cafe = cafeService.selectCafeByNum(num);
        model.addAttribute("cafeBean",cafe);
        return "cafe/update";
    }

    @PostMapping("/cafe/update")
    public String update(@ModelAttribute("cafeBean") @Valid CafeBean cafeBean,
                         BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("cafeBean", cafeBean);
            return "cafe/update";
        }

        CafeEntity cafeEntity = cafeService.insertCafe(cafeBean);
        cafeService.saveCafe(cafeEntity);
        return "redirect:/";
    }

    @ModelAttribute("menuArr")
    public List<String> menu() {
        List<String> menuList = new LinkedList<String>();
        menuList.add("아메리카노");
        menuList.add("카페라떼");
        menuList.add("녹차라떼");
        menuList.add("밀크티");
        return menuList;
    }

    @ModelAttribute("tempArr")
    public List<String> temp() {
        List<String> tempArr = new LinkedList<String>();
        tempArr.add("아이스");
        tempArr.add("핫");
        return tempArr;
    }

    @ModelAttribute("reqArr")
    public List<String> req() {
        List<String> reqArr = new LinkedList<String>();
        reqArr.add("얼음 많이");
        reqArr.add("얼음 적게");
        reqArr.add("많이 뜨겁게");
        reqArr.add("덜 달게");
        return reqArr;
    }
}