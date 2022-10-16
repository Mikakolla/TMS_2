package com.tms.controllers;

import com.tms.entity.Book;
import com.tms.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String getSearchPage(Model model) {
        model.addAttribute("book", new Book());
        return "book_search";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "str") String strForSearch, Model model) {

        List<Book> bookByNameOrAuthor = bookService.getBookByNameOrAuthor(strForSearch);

        model.addAttribute("books", bookByNameOrAuthor);

        return "books_from_db";
    }

    @PostMapping("/add")
    public String addBook(@Valid Book book,
                          BindingResult bindingResultName) {

        bookService.setBook(book);

        return "/book_search";
    }

}
