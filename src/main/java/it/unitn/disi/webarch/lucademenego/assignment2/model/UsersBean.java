package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.io.Serializable;

/**
 * Java Bean containing all information about the webapp's users
 * A normal user will only make use of the variable "user",
 * while an administrator will also have access to "allUsers".
 */
public class UsersBean implements Serializable {
    /**
     * Currently logged-in user
     */
    private User user;

    /**
     * All the webapp's users
     */
    private Users allUsers;

    public UsersBean() {
        allUsers = new Users();
    }

    public Users getAllUsers() {
        return allUsers;
    }

    synchronized public void setAllUsers(Users allUsers) {
        this.allUsers = allUsers;
    }

    public User getUser() {
        return user;
    }

    synchronized public void setUser(User user) {
        this.user = user;
    }

    synchronized public void addUser(User user) {
        allUsers.add(user);
    }
}
