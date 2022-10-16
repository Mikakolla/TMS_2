package com.tms.services;

import com.tms.entity.Book;
import com.tms.exceptions.BookSaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Book> mapper;

    public List<Book> getBookByNameOrAuthor(String strForSearch) {
        return jdbcTemplate.query("SELECT * FROM books where lower(name) like lower(?) or lower(author) like lower(?)", mapper,"%" + strForSearch + "%","%" + strForSearch + "%");
    }

    public void setBook(Book book) {

        List<Book> bookByNameAndAuthor = getBookByNameAndAuthor(book);

        if (bookByNameAndAuthor.size() > 0) {
            throw new BookSaveException("Данная книга уже добавлена");
        } else {
            if (!book.getName().isEmpty() && !book.getAuthor().isEmpty())
                jdbcTemplate.update("INSERT INTO books VALUES (?, ?, ?)", getNextId(), book.getName(), book.getAuthor());
        }
    }

    private List<Book> getBookByNameAndAuthor (Book book) {

        return jdbcTemplate.query("SELECT * FROM books where lower(name) like lower(?) and lower(author) like lower(?)", mapper, book.getName(), book.getAuthor());

    }

    private Integer getNextId() {

        Integer maxRow = jdbcTemplate.queryForObject("SELECT max(id) as max_id FROM books", Integer.class);

        Integer nextId = 1;

        if (maxRow != null) {
            nextId = maxRow + 1;
        }

        return nextId;
    }
}
