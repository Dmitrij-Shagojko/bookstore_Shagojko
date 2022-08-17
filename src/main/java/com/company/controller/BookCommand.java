package com.company.controller;

import com.company.entity.Book;
import com.company.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class BookCommand implements Command {
    private final BookServiceImpl bookServiceImpl;

    public BookCommand(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String rawId = req.getParameter("id");
        Long idBook = Long.parseLong(rawId);
        Book book = bookServiceImpl.getBookById(idBook);
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