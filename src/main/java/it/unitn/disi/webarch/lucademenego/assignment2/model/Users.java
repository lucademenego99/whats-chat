package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing the list of users available in the webapp
 */
public class Users {
    private List<User> users;

    public Users() {
        users = new ArrayList<>();
    }

    /**
     * Write the list of users in basic HTML format, with
     * just a <ul></ul> containing every user
     * @param styles custom styles to be applied, defined in the JSP
     * @return HTML format ready to be shown to the user
     */
    public String toHTML(String styles) {
        StringBuilder usersList = new StringBuilder();
        for (User user : users) {
            usersList.append(user.toHTML(styles));
        }
        return "<ul class='flex flex-col items-center justify-center'>" +
                usersList +
                "</ul>";
    }

    /**
     * Add a user to the list of users
     * @param user the user to add to the list of users
     */
    synchronized public void add(User user) {
        users.add(user);
    }

    synchronized  public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Users{" + "users=[" + users.stream().map(User::toString).map(String::valueOf).collect(Collectors.joining(",")) + "]}";
    }
}
