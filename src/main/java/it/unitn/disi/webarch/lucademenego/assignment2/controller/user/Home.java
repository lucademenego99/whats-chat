package it.unitn.disi.webarch.lucademenego.assignment2.controller.user;

import it.unitn.disi.webarch.lucademenego.assignment2.model.RoomsBean;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UsersBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Home", value = "/user/home")
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the rooms bean
        RoomsBean roomsBean = (RoomsBean) getServletConfig().getServletContext().getAttribute("rooms");
        if (roomsBean == null) {
            roomsBean = new RoomsBean();
            request.getServletContext().setAttribute("rooms", roomsBean);
        }

        // Get the users bean
        UsersBean usersBean = (UsersBean) request.getSession().getAttribute("users");
        if (usersBean == null) {
            // If there is no bean in the current session, something went wrong - 500
            request.setAttribute("status", "500");
            request.setAttribute("errorTitle", "Server Error");
            request.setAttribute("error", "There was an error when verifying your account. Please retry.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/user-home.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("status", "404");
        request.setAttribute("errorTitle", "Page not Found");
        request.setAttribute("error", "The route you are searching for does not exist");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }
}
