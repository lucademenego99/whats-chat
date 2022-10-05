package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.stream.Collectors;

/**
 * Class representing a room
 */
public class Room {
    /**
     * The room's name
     */
    private String name;

    /**
     * All the messages exchanged in this room
     */
    private Messages messages;

    public Room() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    /**
     * Add a new message in the room
     * @param message the message to be added in the room
     */
    synchronized public void addMessage(Message message) {
        this.messages.add(message);
    }

    /**
     * Write the room in basic HTML format, with
     * just a <li></li> containing info about the room
     * @param styles custom styles to be applied, defined in the JSP
     * @param icon custom icon to be put in the <li></li> element
     * @return HTML format ready to be shown to the user
     * @throws UnsupportedEncodingException there was an error encoding the room's name
     */
    public String toHTML(String styles, String icon) throws UnsupportedEncodingException {
        return "<a href='room/" + URLEncoder.encode(this.name, "UTF-8") + "'><li class='" + styles + "'>" +
                    "<p>" + this.name + "</p>" +
                    icon +
                "</li></a>";
    }

    @Override
    public String toString() {
        return "Room{" + "name=" + name + ",messages=[" + messages.messages.stream().map(Message::toString).map(String::valueOf).collect(Collectors.joining(",")) + "]}";
    }
}
