package com.tms.services;

import com.tms.entity.Book;
import com.tms.exceptions.BookSaveException;
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

    public int setBook(Book book) throws SQLException {

        int result;

        List<Book> bookByNameAndAuthor = getBookByNameAndAuthor(book);

        if (bookByNameAndAuthor.size() > 0) {
            throw new BookSaveException("Данная книга уже добавлена");
        } else {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books VALUES (?, ?, ?)");

            preparedStatement.setInt(1, getNextId());
            preparedStatement.setString(2, book.getName());
            preparedStatement.setString(3, book.getAuthor());

            result = preparedStatement.executeUpdate();
        }

        return result;

    }

    private List<Book> getBookByNameAndAuthor (Book book) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books where lower(name) like lower(?) or lower(author) like lower(?)");

        preparedStatement.setString(1, book.getName());
        preparedStatement.setString(2, book.getAuthor());

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Book> books = getBookFromResult(resultSet);

        return books;

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
