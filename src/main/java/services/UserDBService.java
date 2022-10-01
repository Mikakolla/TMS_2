package services;

import entity.User;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDBService {

    private DBService dbService = new DBService();
    private Integer lastIdUser;


    public Set<User> getAllUsers() throws SQLException, ClassNotFoundException {

        Connection connect = dbService.getConnect();

        Statement statement = connect.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

        Set<User> users = new HashSet<>();

        while (resultSet.next()) {
            users.add(getUserFromResultSet(resultSet));
        }

        connect.close();

        return users;
    }

    public User getUserByLogin(String login) throws SQLException, ClassNotFoundException {

        Connection connect = dbService.getConnect();

        PreparedStatement preparedStatement = connect.prepareStatement("SELECT * FROM users where login = ?");

        preparedStatement.setString(1, login);

        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;

        while (resultSet.next()) {
            user = getUserFromResultSet(resultSet);
        }
        connect.close();

        return user;
    }

    public void addNewUser(String login, String password, Boolean sex, String description, Boolean isAdmin) throws SQLException, ClassNotFoundException {

        Connection connect = dbService.getConnect();

        List<Integer> users = getAllUsers().stream().map(User::getId).collect(Collectors.toList());

        lastIdUser = Collections.max(users);

        PreparedStatement preparedStatement = connect.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)");

        preparedStatement.setInt(1, lastIdUser+1);
        preparedStatement.setString(2, login);
        preparedStatement.setString(3, password);
        preparedStatement.setBoolean(4, sex);
        preparedStatement.setString(5, description);
        preparedStatement.setBoolean(6, isAdmin);

        preparedStatement.executeUpdate();

        connect.close();
    }

    public User getUserFromResultSet(ResultSet resultSet) throws SQLException {

            Integer id = resultSet.getInt("user_id");
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            Boolean sex = resultSet.getBoolean("sex");
            String description = resultSet.getString("description");
            Boolean isAdmin = resultSet.getBoolean("isadmin");

            User user = new User(id, login, password, sex, description, isAdmin);

            return new User(id, login, password, sex, description, isAdmin);
    }
}
