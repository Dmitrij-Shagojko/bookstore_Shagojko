package com.company.controller;

import com.company.entity.User;
import com.company.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class UserCommand implements Command {
    private final UserServiceImpl userServiceImpl;

    public UserCommand(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public String execute(HttpServletRequest req){
        String rawId = req.getParameter("id");
        Long idUser = Long.parseLong(rawId);
        User user = userServiceImpl.getUserById(idUser);
        if (user == null){
            int status = 404;
            req.setAttribute("errorStatus", status);
            String message = "The user with the entered ID does not exist";
            req.setAttribute("message", message);
            return "error.jsp";
        }
        req.setAttribute("user", user);
        return "user.jsp";
    }
}
