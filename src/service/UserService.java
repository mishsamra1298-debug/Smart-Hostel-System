package service;

import exception.InvalidInputException;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final List<User> users = new ArrayList<>();

    public void registerUser(User user)
            throws InvalidInputException {

        if (user == null) {
            throw new InvalidInputException(
                    "User cannot be empty."
            );
        }

        if (user.getName() == null ||
                user.getName().trim().isEmpty()) {

            throw new InvalidInputException(
                    "Name is required."
            );
        }

        if (user.getEmail() == null ||
                user.getEmail().trim().isEmpty()) {

            throw new InvalidInputException(
                    "Email is required."
            );
        }

        if (user.getPassword() == null ||
                user.getPassword().length() < 4) {

            throw new InvalidInputException(
                    "Password must contain at least 4 characters."
            );
        }

        users.add(user);

        System.out.println("User registered successfully!");
    }

    public void displayUsers() {

        if (users.isEmpty()) {

            System.out.println("No users registered.");
            return;
        }

        for (User user : users) {

            user.displayUser();
            System.out.println();
        }
    }

    public User findUser(int id) {

        for (User user : users) {

            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public User login(String email, String password) {

        if (email == null || password == null) {
            return null;
        }

        for (User user : users) {

            if (user.getEmail().equalsIgnoreCase(email)
                    && user.getPassword().equals(password)) {

                return user;
            }
        }

        return null;
    }

    public int getTotalUsers() {
        return users.size();
    }
}
