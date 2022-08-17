package com.company.controller;

import com.company.entity.User;
import com.company.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class CreateUserCommand implements Command {
    private final UserServiceImpl userServiceImpl;

    public CreateUserCommand(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || role.isEmpty()){
            int status = 400;
            req.setAttribute("errorStatus", status);
            String message = "Enter the requested data";
            req.setAttribute("message", message);
            return "error.jsp";
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(User.Role.valueOf(role));
        userServiceImpl.create(user);
        req.setAttribute("user", user);
        return "createUserDone.jsp";
    }
}
