package com.example.sample3.service;

import com.example.sample3.bean.BookBean;
import com.example.sample3.entity.BookEntity;
import com.example.sample3.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    BookRepository bookRepository;

    public BookEntity insertBook(BookBean bookBean) {
        System.out.println("BookService insertBook bookBean.getBookstore() : " + bookBean.getBookstore());
        /* bean > entity > save > insert */
        BookEntity bookEntity = modelMapper.map(bookBean, BookEntity.class); //bean을 entity로 바꿈
        return bookEntity;
    }

    public void saveBook(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }


    public List<BookEntity> findByOrderByNoDesc() {
        List<BookEntity> bookList = bookRepository.findByOrderByNoDesc();
        return bookList;
    }


    public BookEntity findByNo(Integer no) {
        BookEntity book = bookRepository.findByNo(no);
        return book;
    }

    public void deleteByNo(int no) {
        bookRepository.deleteById(no);
    }


}
