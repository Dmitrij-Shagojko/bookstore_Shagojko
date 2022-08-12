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
        Book book = bookService.getBookById(idBook);
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>The books is id =" + idBook + ": </h1>");
        writer.write('\n');
        writer.write("<b>Book name: </b>" + book.getName() + "<br>" +
                "<b>Author: </b>" + book.getAuthor() + "<br>" +
                "<b>Publisher: </b>" + book.getPublisher() + "<br>" +
                "<b>Date of publisment: </b>" + book.getPublishmentDate() + "<br>" +
                "<b>ISBN-10: </b>" + book.getISBN10() + "<br>" +
                "<b>ISBN-13: </b>" + book.getISBN13() + "<br>" +
                "<b>Lexile measure</b>: " + book.getLexileMeasure() + "<br>" +
                "<b>Paperback: </b>" + book.getPaperback() + "<br>" +
                "<b>Dimensions: </b>" + book.getDimensions() + "<br>" +
                "<b>Price: </b>" + book.getPrice() + "<br>");
        dataSource.close();
    }
}
