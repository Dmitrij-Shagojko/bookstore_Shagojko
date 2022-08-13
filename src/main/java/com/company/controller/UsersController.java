package com.company.controller;

import com.company.dao.UserDAO;
import com.company.dao.connection.DataSource;
import com.company.dao.impl.UserDAOImpl;
import com.company.entity.User;
import com.company.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class UsersController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = new DataSource();
        UserDAO userDAO = new UserDAOImpl(dataSource);
        UserService userService = new UserService(userDAO);
        List<User> users = userService.getAll();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1> All list of users</h1>");
        writer.write("<table border=\"1\"");
        writer.write("<tr><th> Name </th><th>Last Name </th>" +
                "<th> Email </th></tr>");
        for (int i = 0; i < users.size(); i++) {
            writer.write("<tr><td>" + users.get(i).getFirstName() + "</td>" +
                    "<td><a href=\"http://127.0.0.1:8080/bookstore/user?id=" +
                    users.get(i).getId().toString() + "\" target=\"_blank\">" +
                    users.get(i).getLastName() + "</a></td>" +
                    "<th>" + users.get(i).getEmail() + "</th></tr>");
        }
        writer.write("</table>");
        dataSource.close();
    }
}
