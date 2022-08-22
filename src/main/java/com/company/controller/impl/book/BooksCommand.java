package com.company.controller.impl.book;

import com.company.controller.impl.Command;
import com.company.entity.Book;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class BooksCommand implements Command {
    private final BookService bookService;

    public BooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<Book> books = bookService.getAll();
        req.setAttribute("books", books);
        return "books.jsp";
    }
}