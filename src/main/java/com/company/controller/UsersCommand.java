package com.company.controller;

import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UsersCommand implements Command {
    private final UserService userService;

    public UsersCommand(UserService userService){
        this.userService = userService;
    }
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = userService.getAll();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1> All list of users</h1>");
        writer.write("<table border=\"1\"");
        writer.write("<tr><th> Name </th><th>Last Name </th>" +
                "<th> Email </th></tr>");
        for (User user : users) {
            writer.write("<tr><td>" + user.getFirstName() + "</td>" +
                    "<td><a href=\"http://localhost:8080/bookstore/controller?command=user&id=" +
                    user.getId().toString() + "\" target=\"_blank\">" +
                    user.getLastName() + "</a></td>" +
                    "<th>" + user.getEmail() + "</th></tr>");
        }
        writer.write("</table>");
    }
}
