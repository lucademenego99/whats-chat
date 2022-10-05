package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing the list of rooms available
 */
public class Rooms {
    public List<Room> rooms;

    public Rooms() {
        rooms = new ArrayList<>();
    }

    /**
     * Write the list of rooms in basic HTML format, with
     * just a <ul></ul> containing every room
     * @param styles custom styles to be applied, defined in the JSP
     * @param icon custom icon to be put on each room's item
     * @return HTML format ready to be shown to the user
     */
    public String toHTML(String styles, String icon) throws UnsupportedEncodingException {
        StringBuilder roomsList = new StringBuilder();
        for (Room room : rooms) {
            roomsList.append(room.toHTML(styles, icon));
        }
        return "<ul>" +
                    roomsList +
                "</ul>";
    }

    /**
     * Add a new room to the list of rooms
     * @param room the room to be added
     */
    synchronized public void add(Room room) {
        rooms.add(room);
    }

    @Override
    public String toString() {
        return "Rooms{" + "rooms=[" + rooms.stream().map(Room::toString).map(String::valueOf).collect(Collectors.joining(",")) + "]}";
    }
}
