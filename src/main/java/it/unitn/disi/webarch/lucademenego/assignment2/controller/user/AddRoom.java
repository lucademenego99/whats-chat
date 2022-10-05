package it.unitn.disi.webarch.lucademenego.assignment2.controller.user;

import it.unitn.disi.webarch.lucademenego.assignment2.model.RoomsBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "AddRoom", value = "/user/add-room")
public class AddRoom extends HttpServlet {

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
        String name = request.getParameter("roomname");

        if (name.isEmpty()) {
            request.setAttribute("error", "The room's name cannot be empty");
            request.getRequestDispatcher("/WEB-INF/user-home.jsp").forward(request, response);
        } else if (name.length() > 15) {
            request.setAttribute("error", "The room's name cannot be bigger than 15 characters");
            request.getRequestDispatcher("/WEB-INF/user-home.jsp").forward(request, response);
        } else {
            RoomsBean rooms = (RoomsBean) getServletConfig().getServletContext().getAttribute("rooms");
            if (rooms == null) {
                rooms = new RoomsBean();
                request.getServletContext().setAttribute("rooms", rooms);
            }

            if (rooms.getRooms().rooms.stream().anyMatch(p -> Objects.equals(p.getName(), name))) {
                request.setAttribute("error", "The room's name is already taken");
                request.getRequestDispatcher("/WEB-INF/user-home.jsp").forward(request, response);
            } else {
                rooms.addRoom(name);
                response.sendRedirect("home");
            }
        }
    }
}
