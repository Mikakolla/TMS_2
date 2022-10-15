package com.tms.services;

import com.tms.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {

    @Autowired
    private Connection connection;

    public List<Book> getBookByNameOrAuthor(String strForSearch) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books where lower(name) like lower(?) or lower(author) like lower(?)");

        preparedStatement.setString(1, "%" + strForSearch + "%");
        preparedStatement.setString(2, "%" + strForSearch + "%");

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Book> books = getBookFromResult(resultSet);

        return books;
    }

    public int setBook(String name, String author) throws SQLException {

        System.out.println("add databaseService");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?)");

        preparedStatement.setInt(1, getNextId());
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, author);

        int result = preparedStatement.executeUpdate();

        return result;

    }

    private Integer getNextId() throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(id) as max_id FROM books");

        ResultSet resultSet = preparedStatement.executeQuery();

        int nextId = 0;

        if (resultSet.next()) {
            nextId = resultSet.getInt("max_id") + 1;
        }

        return nextId;
    }

    public List<Book> getBookFromResult(ResultSet resultSet) throws SQLException {

        List<Book> books = new ArrayList<Book>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String author = resultSet.getString("author");

            books.add(new Book(id, name, author));
        }

        return books;
    }
}
