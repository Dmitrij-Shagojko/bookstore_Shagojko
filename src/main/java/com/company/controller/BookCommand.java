package com.company.controller;

import com.company.entity.Book;
import com.company.service.BookService;
import jakarta.servlet.http.HttpServletRequest;

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
        if (book == null){
            int status = 404;
            req.setAttribute("errorStatus", status);
            String message = "The book with the entered ID does not exist";
            req.setAttribute("message", message);
            return "error.jsp";
        }
        req.setAttribute("book", book);
        return "book.jsp";
    }
}