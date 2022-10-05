package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.io.Serializable;

/**
 * Java Bean containing all information about the available rooms
 */
public class RoomsBean implements Serializable {
    /**
     * All the currently available rooms
     */
    private Rooms rooms;

    /**
     * The currently selected room
     */
    private Room selectedRoom;

    public RoomsBean() {
        rooms = new Rooms();
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    /**
     * Add a new room to the list of rooms
     * @param name room's name
     */
    synchronized public void addRoom(String name) {
        Room room = new Room();
        room.setName(name);
        room.setMessages(new Messages());
        rooms.add(room);
    }
}
