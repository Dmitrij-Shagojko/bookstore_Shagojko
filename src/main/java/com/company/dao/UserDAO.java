package com.company.dao;

import com.company.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> getAll();

    User getUserById(Long id);

    User create(User user);

    User update(User user);

    boolean delete(Long id);

    User getUserByEmail(String email);

    List<User> getUserByLastName(String lastName);

    int countAllUsers();
}
