package com.tms.web;


import com.tms.entity.Book;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler
    public String exception(Exception exc, Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("error", exc.getMessage());
        return "book_search";
    }
}
