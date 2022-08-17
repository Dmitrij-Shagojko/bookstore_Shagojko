package com.company.controller;

import com.company.entity.User;
import com.company.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class UsersCommand implements Command {
    private final UserServiceImpl userServiceImpl;

    public UsersCommand(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }
    @Override
    public String execute(HttpServletRequest req){
        List<User> users = userServiceImpl.getAll();
        req.setAttribute("users", users);
        return "users.jsp";
    }
}
