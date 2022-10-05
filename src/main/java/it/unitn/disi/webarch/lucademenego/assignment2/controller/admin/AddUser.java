package it.unitn.disi.webarch.lucademenego.assignment2.controller.admin;

import it.unitn.disi.webarch.lucademenego.assignment2.model.User;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UserDAO;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UsersBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.NamingException;
import java.io.IOException;

@WebServlet(name = "AddUser", value = "/admin/add-user")
public class AddUser extends HttpServlet {
    UserDAO userDAO;

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
        request.setAttribute("status", "404");
        request.setAttribute("errorTitle", "Page not Found");
        request.setAttribute("error", "The link you clicked may be broken, or the page has been removed or renamed");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");

        try {
            userDAO.signup(username, pwd);
            // Get the users bean
            UsersBean usersBean = (UsersBean) request.getSession().getAttribute("users");
            if (usersBean == null) {
                usersBean = new UsersBean();
                request.getSession().setAttribute("users", usersBean);
            }

            // Add the new user to the users bean
            User toAdd = new User();
            toAdd.setUsername(username);
            toAdd.setIsAdmin(false);
            usersBean.addUser(toAdd);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/admin-dashboard.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("dashboard");
    }
}
