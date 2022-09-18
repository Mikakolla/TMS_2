package com.tms.homeworks.homework20;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/tms", "postgres", "postgres");

        Statement statement = connection.createStatement();
        Main main = new Main();

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet checkTable = databaseMetaData.getTables(null, null, "brand", null);

        if (!checkTable.next()) {
            main.createTable(statement);
        }
        main.insertNode(statement);
        main.usePreparedStatement(connection);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM brand");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
        }
    }

    public void createTable(Statement statement) throws SQLException {

        statement.execute("CREATE TABLE brand(id integer primary key, name varchar (100));");

    }

    public void insertNode(Statement statement) throws SQLException {

        statement.execute("INSERT INTO brand VALUES (1, 'VOLVO')");
        statement.execute("INSERT INTO brand VALUES (2, 'AUDI')");
        statement.execute("INSERT INTO brand VALUES (3, 'BMW')");

    }

    public void usePreparedStatement(Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM brand where id = ?");

        preparedStatement.setInt(1, 1);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
        }
    }
}
