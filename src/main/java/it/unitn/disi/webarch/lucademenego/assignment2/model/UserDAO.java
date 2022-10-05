package it.unitn.disi.webarch.lucademenego.assignment2.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class following the DAO architectural patten, exposing methods
 * to access the users saved in the storage.
 * The class is a singleton: its constructor is protected, and the servlets
 * will only get access to it by using the "getInstance" function. In this way,
 * one only instance of it will be actually used among all the requests.
 */
public class UserDAO {
    /**
     * Instance of UserDAO
     */
    private static UserDAO instance;

    /**
     * The admin's username
     */
    private final String adminUsername;

    /**
     * The admin's password
     */
    private final String adminPassword;

    /**
     * Data Source in which we can find the file containing information about the users
     */
    private final String dataSource;

    /**
     * Map of the users, where the key is the username and the value is the password
     */
    private Map<String, String> users;

    /**
     * Protected Constructor
     * Get all the information about normal users (from a file) and about the administrator (from the env)
     * @throws NamingException The environment variables could not be found
     */
    protected UserDAO() throws NamingException {
        // Get Data Source, Admin Username and Admin Password from the environment
        Context ctx = new InitialContext();
        adminUsername = (String) ctx.lookup("java:comp/env/AdminUsername");
        adminPassword = (String) ctx.lookup("java:comp/env/AdminPassword");
        dataSource = (String) ctx.lookup("java:comp/env/UsersPath");

        // Load the users from users.credentials
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(dataSource)));
            users = (Map<String, String>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("[UserDAO] - Data Source not found");
            users = new HashMap<>();
        }
    }

    /**
     * Perform the login
     * @param username user's username
     * @param password user's password
     * @return null if the user is found, otherwise the User itself
     */
    public User login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            User user = new User();
            user.setUsername(username);
            user.setIsAdmin(false);
            return user;
        } else if (Objects.equals(username, adminUsername) && Objects.equals(password, adminPassword)) {
            User user = new User();
            user.setUsername(username);
            user.setIsAdmin(true);
            return user;
        }
        return null;
    }

    /**
     * Create a new user
     * @param username user's username
     * @param password user's password
     * @throws Exception the parameters are not correct or there was an error creating the user
     */
    public void signup(String username, String password) throws Exception {
        if (username.isEmpty() || password.isEmpty()) {
            throw new Exception("Username and password cannot be empty");
        }

        if (users.containsKey(username)) {
            throw new Exception("Username already taken");
        }

        if (username.length() > 15) {
            throw new Exception("The username can't be longer than 30 characters");
        }

        users.put(username, password);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(dataSource)));
            oos.writeObject(users);
            oos.close();
        } catch (IOException e) {
            System.err.println("[UserDAO] - " + e);
            throw e;
        }
    }

    /**
     * Get all the users as a Users object
     * @return All the users
     */
    public Users getUsers() {
        Users allUsers = new Users();
        users.forEach((key, index) -> {
            User u = new User();
            u.setUsername(key);
            u.setIsAdmin(false);
            allUsers.add(u);
        });
        return allUsers;
    }

    /**
     * Get the instance of the UserDAO singleton, or create it
     * @return The instance of UserDAO
     * @throws NamingException The environment variables could not be found
     */
    public static UserDAO getInstance() throws NamingException {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

}
