package com.company.controller.impl.user;

import com.company.controller.impl.Command;
import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

public class UserCommand implements Command {
    private final UserService userService;

    public UserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req){
        String rawId = req.getParameter("id");
        Long idUser = Long.parseLong(rawId);
        User user = userService.getUserById(idUser);
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