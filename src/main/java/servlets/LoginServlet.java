package servlets;

import entity.User;
import services.UserDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserDBService userDBService = new UserDBService();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user;
        try {
            user = userDBService.getUserByLogin(login);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (user != null && user.getPassword().equals(password)) {
            if (user.getIsAdmin()) {
                resp.sendRedirect("/user/create_user");
            }
        } else {
            resp.sendRedirect("user_page.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.html");
    }
}
