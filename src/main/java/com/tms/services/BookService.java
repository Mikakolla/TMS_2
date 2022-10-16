package com.tms.services;

import com.tms.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private DatabaseService databaseService;

    public List<Book> getBookByNameOrAuthor(String strForSearch) {

        return databaseService.getBookByNameOrAuthor(strForSearch);

    }

    public void setBook(Book book) {

        databaseService.setBook(book);

    }
}
