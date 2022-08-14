package com.company.controller;

import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class UserCommand implements Command {
    private final UserService userService;

    public UserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String rawId = req.getParameter("id");
        Long idUser = Long.parseLong(rawId);
        User user = userService.getUserById(idUser);
        PrintWriter writer = resp.getWriter();
        if (user.getId() != null) {
            resp.setContentType("text/html");
            writer.write("<h1>The user with id =" + idUser + ": </h1>");
            writer.write('\n');
            writer.write("<b>User name: </b>" + user.getFirstName() + "<br>" +
                    "<b>User last name: </b>" + user.getLastName() + "<br>" +
                    "<b>email: </b>" + user.getEmail() + "<br>" +
                    "<b>user role: </b>" + user.getRole() + "<br>");
        } else {
            resp.sendError(404, "User with id = " + idUser + " - not found");
        }
    }
}
