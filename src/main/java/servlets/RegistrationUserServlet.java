package servlets;

import entity.User;
import exceptions.UserException;
import services.UserDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/create_user")
public class RegistrationUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("admin_page.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDBService userDBService = new UserDBService();

        String login = req.getParameter("login");
        try {
            if (userDBService.getUserByLogin(login) == null) {

                String password = req.getParameter("password");
                Boolean sex = req.getParameter("sex").equals("man");
                String description = req.getParameter("description");
                Boolean isAdmin = req.getParameter("role").equals("admin");

                userDBService.addNewUser(login, password, sex, description, isAdmin);

                resp.sendRedirect("/user/user_added.html");

            } else {
                throw new UserException("user exists.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
