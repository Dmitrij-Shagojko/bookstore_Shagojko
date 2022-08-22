package com.company.service;

import com.company.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getUserById(Long id);

    User create(User user);

    User update(User user);

    void delete(Long id);

    User getUserByEmail(String email);

    List<User> getUserByLastName(String lastName);

    int countAllUsers();
}
