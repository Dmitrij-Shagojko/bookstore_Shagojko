package com.company.controller;

import com.company.dao.UserDAO;
import com.company.dao.connection.DataSource;
import com.company.dao.impl.UserDAOImpl;
import com.company.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rawId = req.getParameter("id");
        Long idUser = Long.parseLong(rawId);
        DataSource dataSource = new DataSource();
        UserDAO userDAO = new UserDAOImpl(dataSource);
        User user = userDAO.getUserById(idUser);
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>The user with id =" + idUser + ": </h1>");
        writer.write('\n');
        writer.write("<b>User name: </b>" + user.getFirstName() + "<br>" +
                "<b>User last name: </b>" + user.getLastName() + "<br>" +
                "<b>email: </b>" + user.getEmail() + "<br>" +
                "<b>user role: </b>" + user.getRole() + "<br>");
        dataSource.close();
    }
}
