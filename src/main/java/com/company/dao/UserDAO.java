package com.company.dao;

import com.company.entity.User;

import java.util.List;

public interface UserDAO extends AbstractDao<Long, User> {

    User getUserByEmail(String email);

    List<User> getUserByLastName(String lastName);

}
