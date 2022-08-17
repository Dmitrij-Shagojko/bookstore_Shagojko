package com.company.controller;

import com.company.entity.Book;
import com.company.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class BooksCommand implements Command {
    private final BookServiceImpl bookServiceImpl;

    public BooksCommand(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<Book> books = bookServiceImpl.getAll();
        req.setAttribute("books", books);
        return "books.jsp";
    }
}