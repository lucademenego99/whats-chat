package it.unitn.disi.webarch.lucademenego.assignment2.controller.user;

import it.unitn.disi.webarch.lucademenego.assignment2.model.Message;
import it.unitn.disi.webarch.lucademenego.assignment2.model.RoomsBean;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UsersBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

@WebServlet(name = "AddMessage", value = "/user/add-message")
public class AddMessage extends HttpServlet {
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
        String message = request.getParameter("message");

        if (message.isEmpty()) {
            request.setAttribute("error", "The message cannot be empty");
            request.getRequestDispatcher("/WEB-INF/room.jsp").forward(request, response);
        } else {
            RoomsBean rooms = (RoomsBean) getServletConfig().getServletContext().getAttribute("rooms");
            if (rooms == null) {
                // Unexpected error - 500
                request.setAttribute("status", "500");
                request.setAttribute("errorTitle", "Server Error");
                request.setAttribute("error", "It seems you are sending a message in a room that does not exist. Please retry.");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
                return;
            }

            // Get the users bean
            UsersBean usersBean = (UsersBean) request.getSession().getAttribute("users");
            if (usersBean == null) {
                // Unexpected error - 403
                request.setAttribute("status", "403");
                request.setAttribute("errorTitle", "Unauthorized");
                request.setAttribute("error", "It seems you are not authenticated yet.");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
                return;
            }

            Message toSend = new Message();
            toSend.setText(message);
            toSend.setUser(usersBean.getUser().getUsername());
            toSend.setTimestamp(new Timestamp(System.currentTimeMillis()));

            rooms.getSelectedRoom().addMessage(toSend);

            System.out.println(rooms.getSelectedRoom().toString());

            response.sendRedirect("room/" + rooms.getSelectedRoom().getName());
        }
    }
}
