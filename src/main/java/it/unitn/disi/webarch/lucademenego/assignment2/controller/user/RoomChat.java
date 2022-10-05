package it.unitn.disi.webarch.lucademenego.assignment2.controller.user;

import it.unitn.disi.webarch.lucademenego.assignment2.model.Room;
import it.unitn.disi.webarch.lucademenego.assignment2.model.RoomsBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "Room", value = "/user/room/*")
public class RoomChat extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the room from the URL
        String room = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");

        // Get the rooms bean
        RoomsBean rooms = (RoomsBean) getServletConfig().getServletContext().getAttribute("rooms");
        // Check if the room actually exists
        if (rooms == null) {
            // Unexpected error - there are no rooms
            request.setAttribute("status", "404");
            request.setAttribute("errorTitle", "Room not Found");
            request.setAttribute("error", "The room you requested cannot be found. Maybe it was deleted");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } else {
            // Find the chosen room based on the given URL
            Optional<Room> chosenRoom = rooms.getRooms().rooms.stream().filter(r -> Objects.equals(r.getName(), room)).findFirst();
            if (chosenRoom.isPresent()) {
                rooms.setSelectedRoom(chosenRoom.get());
                request.getRequestDispatcher("/WEB-INF/room.jsp").forward(request, response);
            } else {
                // The room doesn't exist
                request.setAttribute("status", "404");
                request.setAttribute("errorTitle", "Room not Found");
                request.setAttribute("error", "The room you requested cannot be found. Maybe it was deleted");
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }
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
