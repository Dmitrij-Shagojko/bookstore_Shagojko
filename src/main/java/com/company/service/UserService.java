package com.company.service;

import com.company.dao.UserDAO;
import com.company.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService {
    private static final Logger log = LogManager.getLogger(UserService.class);
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAll() {
        log.debug("Use method getAll");
        List<User> users = userDAO.getAll();
        return users;
    }

    public User getUserById(Long id) {
        log.debug("Use method getUserById. Where ID: {}", id);
        User user = userDAO.getUserById(id);
        if (user == null) {
            try {
                throw new RuntimeException("User with id = " + id + " - not found");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        return user;
    }

    public User create(User user) {
        log.debug("Use method create. Where user: {}", user);
        User newUser = userDAO.create(user);
        if (newUser == null) {
            try {
                throw new RuntimeException("Failed to create new user!");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        return newUser;
    }

    public User update(User user) {
        log.debug("Use method update. Where user: {}", user);
        User upUser = userDAO.update(user);
        if (upUser == null) {
            try {
                throw new RuntimeException("User " + user.getId() + " is not update!");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        return upUser;
    }

    public void delete(Long id) {
       log.debug("Use method delete. Where ID: {}", id);
        if (!userDAO.delete(id)) {
            try {
                throw new RuntimeException("Couldn't delete user (id = " + id + ");");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public User getUserByEmail(String email) {
       log.debug("Use method getUserByEmail. Where email: {}", email);
        User user = userDAO.getUserByEmail(email);
        if (user == null) {
            try {
                throw new RuntimeException("User with email: " + email + " - not founded");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        return user;
    }

    public List<User> getUserByLastName(String lastName) {
        log.debug("Use method getUserByLastName. Where last name: {}", lastName);
        List<User> users = userDAO.getUserByLastName(lastName);
        return users;
    }

    public int countAllUsers() {
        log.debug("Use method countAllUsers");
        int count = userDAO.countAllUsers();
        if (count == 0) {
            try {
                throw new RuntimeException("List of users is empty!");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        return count;
    }
}
