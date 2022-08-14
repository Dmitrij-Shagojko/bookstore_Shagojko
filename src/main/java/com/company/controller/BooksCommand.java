package com.company.controller;

import com.company.entity.Book;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BooksCommand implements Command {
    private final BookService bookService;

    public BooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Book> books = bookService.getAll();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1> All list of books</h1>");
        writer.write("<table border=\"1\"");
        writer.write("<tr><th> Book name </th>" +
                "<th> Author </th>" + "<th> Language </th>" + "<th> Price, $ </th></tr>");
        for (Book book : books) {
            writer.write("<tr><td>" +
                    "<a href=\"http://localhost:8080/bookstore/controller?command=book&id=" +
                    book.getId().toString() + "\" target=\"_blank\">" +
                    book.getName() + "</a></td>" +
                    "<td>" + book.getAuthor() + "</td>" +
                    "<td>" + book.getLanguage() + "</td>" +
                    "<th>" + book.getPrice() + "</th></tr>");
        }
        writer.write("</table>");
    }
}