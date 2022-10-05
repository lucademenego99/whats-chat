package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Class representing a message of a given room
 */
public class Message {
    /**
     * Username of the user who has sent the message
     */
    private String user;

    /**
     * The text of the message
     */
    private String text;

    /**
     * Timestamp representing when the message was sent
     */
    private Timestamp timestamp;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Write the room in basic HTML format, with
     * just a <li></li> containing info about the room
     * @param styleSender custom style to be applied if the sender was the logged user
     * @param styleReceiver custom style to be applied if the sender was not the logged user
     * @param sender username of the currently logged-in user
     * @return HTML format ready to be shown to the user
     */
    public String toHTML(String styleSender, String styleReceiver, String sender) {
        return "<li class='mb-4 flex w-full " + (Objects.equals(sender, user) ? "justify-end" : "justify-start") + "'>" +
                    "<div class='" + (Objects.equals(sender, user) ? styleSender : styleReceiver) + "'>" +
                        "<p class='font-bold text-sm'>" + this.user + "</p>" +
                        "<p class='font-sans text-sm'>" + this.text + "</p>" +
                        "<p class='self-end text-xs font-light mt-2'>" + new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(this.timestamp) + "</p>" +
                    "</div>" +
                "</li>";
    }

    @Override
    public String toString() {
        return "Message{" + "user=" + user + ",text=" + text + ",timestamp=" + timestamp + "}";
    }
}
