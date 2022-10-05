package it.unitn.disi.webarch.lucademenego.assignment2.controller;

import it.unitn.disi.webarch.lucademenego.assignment2.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        try {
            userDAO = UserDAO.getInstance();
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get username and password from the request
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");

        // Try to perform the login
        User user = userDAO.login(username, pwd);

        // Check the login's result
        if (user == null) {
            request.setAttribute("error", "Invalid username/password. Please try again.");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();

            // Get the users bean
            UsersBean usersBean = (UsersBean) session.getAttribute("users");
            if (usersBean == null) {
                usersBean = new UsersBean();
                session.setAttribute("users", usersBean);
            }

            // The user is now authenticated
            session.setAttribute("auth", true);
            usersBean.setUser(user);

            // Add the list of users to the user object if the user is an administrator
            if (user.getIsAdmin()) {
                usersBean.setAllUsers(userDAO.getUsers());
                response.sendRedirect("admin/dashboard");
            } else {
                response.sendRedirect("user/home");
            }
        }
    }
}
