package com.company.controller;

import com.company.entity.Book;
import com.company.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class BookCommand implements Command {
    private final BookService bookService;

    public BookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String rawId = req.getParameter("id");
        Long idBook = Long.parseLong(rawId);
        Book book = bookService.getBookById(idBook);
        req.setAttribute("book", book);
        return "book.jsp";
    }
}