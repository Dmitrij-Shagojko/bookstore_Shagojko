package com.company.controller;

import com.company.dao.BookDAO;
import com.company.dao.connection.DataSource;
import com.company.dao.impl.BookDAOImpl;
import com.company.entity.Book;
import com.company.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/book")
public class BookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rawId = req.getParameter("id");
        Long idBook = Long.parseLong(rawId);
        DataSource dataSource = new DataSource();
        BookDAOImpl bookDAO = new BookDAOImpl(dataSource);
        BookService bookService = new BookService(bookDAO);
        BookService bookService1 = new BookService(new BookDAOImpl(new DataSource()));
        Book book = bookService.getBookById(idBook);
        PrintWriter writer = resp.getWriter();
        writer.write("The books is id =" + idBook + ";");
    }
}
