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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/books")
public class BooksController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSource dataSource = new DataSource();
        BookDAO bookDAO = new BookDAOImpl(dataSource);
        BookService bookService = new BookService(bookDAO);
        List<Book> books = bookService.getAll();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1> All list of books</h1>");
        writer.write("<table border=\"1\"");
        writer.write("<tr><th> Book name </th>" +
                "<th> Author </th>" + "<th> Language </th>" + "<th> Price, $ </th></tr>");
        for (int i = 0; i < books.size(); i++) {
            writer.write("<tr><td>" +
                    "<a href=\"http://127.0.0.1:8080/bookstore/book?id=" +
                    books.get(i).getId().toString() + "\" target=\"_blank\">" +
                    books.get(i).getName() + "</a></td>" +
                    "<td>" + books.get(i).getAuthor() + "</td>" +
                    "<td>" + books.get(i).getLanguage() + "</td>" +
                    "<th>" + books.get(i).getPrice() + "</th></tr>");
        }
        writer.write("</table>");
        dataSource.close();
    }
}
