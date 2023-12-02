/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos;

/**
 *
 * @author fatimabintetariq
 */
import java.util.ArrayList;

public class UserAuthentication {
    private ArrayList<User> users;

    public UserAuthentication() {
        users = new ArrayList<>();
        // Add sample users
        users.add(new User("manager", "manager123", "Manager"));
        users.add(new User("assistant", "assistant123", "Sales Assistant"));
    }

    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Authentication failed
    }
}
