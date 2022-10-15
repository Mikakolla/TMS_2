package com.tms.services;

import com.tms.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private DatabaseService databaseService;

    public List<Book> getBookByNameOrAuthor(String strForSearch) throws SQLException {

        return databaseService.getBookByNameOrAuthor(strForSearch);

    }

    public int setBook(String name, String author) throws SQLException {

        return databaseService.setBook(name, author);

    }
}
