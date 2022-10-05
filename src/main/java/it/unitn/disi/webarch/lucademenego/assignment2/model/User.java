package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing a user
 */
public class User {
    /**
     * The user's username
     */
    private String username;

    /**
     * Is the user an administrator?
     */
    private boolean isAdmin;

    public User() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Write the user in basic HTML format, with
     * just a <li></li> containing info about the user
     * @param styles custom styles to be applied, defined in the JSP
     * @return HTML format ready to be shown to the user
     */
    public String toHTML(String styles) {
        return "<li class='" + styles + "'>" +
                    "<p>" + this.username + "</p>" +
                "</li>";
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ",isAdmin=" + isAdmin + "}";
    }
}
