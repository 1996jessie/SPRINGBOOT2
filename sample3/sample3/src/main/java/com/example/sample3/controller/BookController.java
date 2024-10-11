package com.example.sample3.controller;

import com.example.sample3.bean.BookBean;
import com.example.sample3.entity.BookEntity;
import com.example.sample3.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor //final 변수가 있으면 생성자 만들어줌
public class BookController {

    private final BookService bookService;

    @GetMapping(value={"/","books"})
    public String getAllBooks(Model model){
        List<BookEntity> bookList = bookService.findByOrderByNoDesc();
        model.addAttribute("bookList",bookList);
        return "book/select";
    }

    // select.html에서 추가 클릭
    @GetMapping(value = "/book/insert")
    public String insertBook(Model model){
        System.out.println("/book/insert get 방식");
        model.addAttribute("bookBean", new BookBean());
        return "book/insert";
    }

    @PostMapping(value = "/book/insert")
    public String insertBookPost(@ModelAttribute(value = "bookBean") @Valid BookBean bookBean,
                                 BindingResult result, Model model){
        System.out.println("/book/insert post 방식");

        if(result.hasErrors()) {
            model.addAttribute("bookBean", bookBean);
            return "book/insert";
        }

        System.out.println("bookBean.getBookstore() : " + bookBean.getBookstore());
        BookEntity bookEntity = bookService.insertBook(bookBean);
        bookService.saveBook(bookEntity);

        return "redirect:/books";
    }

    @GetMapping(value = "book/detail")
    public String detailBook(@RequestParam("no") Integer no, Model model) {
        System.out.println("BookController book detail no : " + no);
        BookEntity bookEntity = bookService.findByNo(no);
        model.addAttribute("book", bookEntity);
        return "book/detail";
    }

    @GetMapping("/book/delete")
    public String delete(@RequestParam("no") int no){
        System.out.println("/book/delete get no:" + no);
        bookService.deleteByNo(no);
        return "redirect:/";
    }

    @PostMapping("book/checkDelete")
    public String checkDelete(@RequestParam("row") int[] rowcheck) {
        System.out.println("book/checkDelete Post 요청");

        for(int i = 0; i < rowcheck.length; i++) {
            System.out.println("book/checkDelete() rowcheck : " + rowcheck[i]);
            bookService.deleteByNo(rowcheck[i]);
        }

        return "redirect:/books";
    }

    @GetMapping("book/update/{no}")
    public String update(@PathVariable("no") int no, Model model) {
        System.out.println("book/update get no : " + no);
        BookEntity bookEntity = bookService.findByNo(no);
        String[] bookstoreArr = {"교보문고", "알라딘", "yes24", "인터파크"};
        model.addAttribute("bookBean", bookEntity);
        model.addAttribute("bookstoreArr", bookstoreArr);
        return "book/update";

    }

    @ModelAttribute("kindArr")
    public List<String> kind() {
        List<String> kindList = new LinkedList<String>();
        kindList.add("유료");
        kindList.add("무료");
        return kindList;
    }

    @PostMapping(value = "/book/update")
    public String updateBookPost(@ModelAttribute(value = "bookBean") @Valid BookBean bookBean,
                                 BindingResult result, Model model){
        System.out.println("/book/update post 방식");

        if(result.hasErrors()) {
            model.addAttribute("bookBean", bookBean);
            return "book/update";
        }

        BookEntity bookEntity = bookService.insertBook(bookBean);
        bookService.saveBook(bookEntity); //pk가 존재하지 않으면 insert, 존재하면 update
        return "redirect:/books";
    }
}

