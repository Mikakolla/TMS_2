package com.tms.services;

import com.tms.entity.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String author = resultSet.getString("author");
        return new Book(id, name, author);
    }
}
