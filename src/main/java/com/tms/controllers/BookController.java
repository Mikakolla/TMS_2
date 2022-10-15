package com.tms.controllers;

import com.tms.entity.Book;
import com.tms.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String getSearchPage() {
        return "book_search";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "str") String strForSearch, Model model) throws SQLException {

        List<Book> bookByNameOrAuthor = bookService.getBookByNameOrAuthor(strForSearch);

        model.addAttribute("books", bookByNameOrAuthor);

        return "books_from_db";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam(value = "name") String name,
                          @RequestParam(value = "author") String author,
                          Model model) throws SQLException {

        int result = bookService.setBook(name, author);

        String addBook = "Книга добавлена";

        if (result == 1) {
            model.addAttribute("result", addBook);
        }

        return "/book_search";
    }

}
