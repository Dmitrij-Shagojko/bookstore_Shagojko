package com.company.controller;

import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class UsersCommand implements Command {
    private final UserService userService;

    public UsersCommand(UserService userService){
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest req){
        List<User> users = userService.getAll();
        req.setAttribute("users", users);
        return "users.jsp";
    }
}
