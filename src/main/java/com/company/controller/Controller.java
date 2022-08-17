package com.company.controller;

import com.company.controller.impl.Command;
import com.company.controller.impl.CommandFactory;
import com.company.dao.connection.DataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processing(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processing(req, resp);
    }

    private void processing(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandParam = req.getParameter("command");
        Command command = CommandFactory.INSTANCE.getCommand(commandParam);
        String page;
        try {
            page = command.execute(req);
        } catch (NullPointerException e) {
            int status = 400;
            req.setAttribute("errorStatus", status);
            String message = "Wrong command entered";
            req.setAttribute("message", message);
            page = "error.jsp";
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }


    @Override
    public void destroy() {
        DataSource.INSTANCE.close();
    }
}