package com.example.sample4.controller;

import com.example.sample4.bean.MovieBean;
import com.example.sample4.entity.MovieEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.sample4.service.MovieService;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /*
    @GetMapping(value = {"/","/movies"} ) // 목록 조회, value 생략 가능
    public String selectAll(Model model){

        List<MovieEntity> movieList = movieService.selectAllMovie();

        model.addAttribute("movieList", movieList);
        return "/movie/select";
    }
    */

    /*검색어, 페이지 설정*/
    @GetMapping(value = {"/","/movies"} ) // 목록 조회, value 생략 가능
    public String selectAll(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "3") int size,
                            @RequestParam(required = false) String keyword,
                            Model model){

        long totalCount =  movieService.getTotalCount();
        Page<MovieEntity> movieList = movieService.getMovieEntity(page,size,keyword);


        System.out.println("keyword : " + keyword);
        System.out.println("page : " + page);
        System.out.println("size : " + size);
        System.out.println("movieList.getSize() :"+ movieList.getSize()); //
        System.out.println("movieList.getTotalElements() :"+ movieList.getTotalElements()); // 전체, 검색
        System.out.println("movieList.getNumberOfElements() :"+ movieList.getNumberOfElements());
        System.out.println("movieList.getNumber() :"+ movieList.getNumber()); // p1=>0, p2=>1
        System.out.println("movieList.getTotalPages() :"+ movieList.getTotalPages()); //

        System.out.println("totalCount :"+ totalCount);


        model.addAttribute("movieList", movieList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("getTotalElements", movieList.getTotalElements());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);

        return "/movie/select";
    }

    @GetMapping(value = "/movie/insert")
    public String insertGet(Model model){
        System.out.println("/book/insert get방식");
        model.addAttribute("movieBean", new MovieBean());

        return "movie/insert";
    }//insertGet

    // movie/insert.html에서 등록 클릭
    @PostMapping(value="/movie/insert")
    public String insertMoviePost(@ModelAttribute("movieBean") @Valid MovieBean movieBean , BindingResult result,
                                  Model model){
        //System.out.println("/movie/insert post방식");

        if(result.hasErrors()){
            model.addAttribute("movieBean", movieBean);
            return "movie/insert";
        }

        MovieEntity movieEntity = movieService.insertMovie(movieBean);
        movieService.saveMovie(movieEntity);

        return "redirect:/";
    }//insertMoviePost


    @PostMapping(value="/movie/idCheck/{id}")
    //@ResponseBody
    public @ResponseBody String idCheck(@PathVariable("id") String id){
        System.out.println("/movie/idCheck/ id:" + id);
        MovieEntity movie = movieService.findMovieById(id);
        System.out.println(movie);
        String message ;
        if(movie == null){
            message = "YES";
        }else{
            message = "NO";
        }
        return message;

    }//idCheck

//    @{/movie/detail(num=${mv.num})}
//    @GetMapping(value = "movie/detail")
//    public String content(@RequestParam(value = "num") int num, Model model){

    //    @{'/movie/detail/'+${mv.num}}
    @GetMapping("/movie/detail/{num}")
    public String detail(@PathVariable("num") int num, Model model){

        MovieEntity me = movieService.selectMovieByNum(num);

        model.addAttribute("movie", me);

        return "movie/detail";
    }

    @GetMapping("/movie/delete")
    public String delete(@RequestParam("no") int no){
        movieService.deleteById(no);
        return "redirect:/";
    }

    @PostMapping(value="/movie/checkDelete")
    public String checkDelete(@RequestParam("row") int[] row) {
        for(int i=0; i<row.length; i++) {
            movieService.deleteById(row[i]);
        }

        return "redirect:/";
    }

    @GetMapping("/movie/update/{no}")
    public String update(@PathVariable("no") int no, Model model){
        MovieEntity movie = movieService.selectMovieByNum(no);
        model.addAttribute("movieBean",movie);
        return "movie/update";
    }

    @PostMapping("/movie/update")
    public String update(@ModelAttribute("movieBean") @Valid MovieBean movieBean,
                         BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("movieBean", movieBean);
            return "movie/update";
        }

        MovieEntity movieEntity = movieService.insertMovie(movieBean);
        movieService.saveMovie(movieEntity);
        return "redirect:/";
    }

    @ModelAttribute("timeArr")
    public List<String> time() {
        List<String> timeList = new LinkedList<String>();
        timeList.add("08~10");
        timeList.add("10~12");
        timeList.add("12~14");
        timeList.add("14~16");
        timeList.add("16~18");
        timeList.add("18~20");
        return timeList;
    }

    @ModelAttribute("genreArr")
    public List<String> genre() {
        List<String> genreArr = new LinkedList<String>();
        genreArr.add("공포");
        genreArr.add("코미디");
        genreArr.add("액션");
        genreArr.add("애니메이션");
        return genreArr;
    }
}